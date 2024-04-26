package com.lemon.commons;

import com.alibaba.fastjson.JSONObject;
import com.lemon.pojo.CaseInfo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-25 20:15
 * @Desc：
 **/
public class BaseTest {

    @BeforeSuite
    public void init() {
        //配置restAssured小数类型的返回值
        RestAssured.config = RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));
        //REST-assured基础 baseurl设置
        RestAssured.baseURI = Constants.BASE_URL;
    }

    /**
     * 根据请求method模拟不同类型的http请求
     * @param caseInfo 包含 请求头、请求体、url、请求method
     * @return 响应对象
     */
    public Response request(CaseInfo caseInfo) {
        //请求头
        Map map = JSONObject.parseObject(caseInfo.getRequestHeader(), Map.class);
        //请求参数
        String inputParams = caseInfo.getInputParams();
        //请求url
        String url = caseInfo.getUrl();
        //请求method
        String method = caseInfo.getMethod();
        Response response = null;
        if("post".equalsIgnoreCase(method)) {
            response = RestAssured.given().log().all().headers(map).body(inputParams).when().post(url).then().log().all().extract().response();
        }else if("get".equalsIgnoreCase(method)) {
            //get 一般情况下参数都跟在url后，不需要请求体携带参数，如果有特殊get请求，也可加上body
            response = RestAssured.given().log().all().headers(map).when().get(url).then().log().all().extract().response();
        }else if("patch".equalsIgnoreCase(method)) {
            response = RestAssured.given().log().all().headers(map).body(inputParams).when().patch(url).then().log().all().extract().response();
        }else if("put".equalsIgnoreCase(method)) {
            response = RestAssured.given().log().all().headers(map).body(inputParams).when().put(url).then().log().all().extract().response();
        }else if("delete".equalsIgnoreCase(method)) {
            response = RestAssured.given().log().all().headers(map).body(inputParams).when().delete(url).then().log().all().extract().response();
        }
        return response;
    }


    /**
     * 响应断言
     * @param caseInfo      获取期望值
     * @param response      响应对象
     */
    public void assertResponse(CaseInfo caseInfo, Response response) {
        //EXCEL中json格式的期望值转成Map  {"code": 0,"msg": "OK"}
        String expected = caseInfo.getExpected();
        if (StringUtils.isNotBlank(expected)) {
            Map<String, Object> expectedMap = JSONObject.parseObject(caseInfo.getExpected(), Map.class);
            //遍历Map
            expectedMap.forEach((gPath, expectedValue) -> {
                //key：找到实际值的表达式
                //value：期望值
                Object actual = response.jsonPath().get(gPath);
                System.out.println("actual:" + actual);
                System.out.println("expectedValue:" + expectedValue);
                Assert.assertEquals(actual, expectedValue);
            });
        }
    }

    /**
     * 从响应中提出参数保存到环境变量中
     * @param caseInfo      getExtractExper(),提出参数表达式
     * @param response      响应对象
     */
    public void extractParas2Env(CaseInfo caseInfo, Response response) {
        //获取提取参数表达式 {"mobile_phone":"data.mobile_phone","member_id":"data.id"}
        String extractExper = caseInfo.getExtractExper();
        //判断表达式是否为空
        if(StringUtils.isNotBlank(extractExper)) {
            //Json表达式转成Map
            Map<String, Object> map = JSONObject.parseObject(extractExper, Map.class);
            map.forEach((key, gPath) -> {
                //通过gPath表达式获取到实际需要提取的值。
                Object actual = response.jsonPath().get(gPath.toString());
                //表达式key和实际提取的值保存到环境变量中。
                //{member_id=1237008423, mobile_phone=13345454548}
                Environment.envMap.put(key, actual);

            });
        }
    }


    /**
     * 替换用例中出现的参数化变量，主要替换列：输入参数、期望返回值、url、请求头
     * @param caseInfo      用例信息
     */
    public void replaceParams(CaseInfo caseInfo) {
        //替换输入参数
        String params = Environment.replaceParams(caseInfo.getInputParams());
        caseInfo.setInputParams(params);
        //替换期望返回值
        String params2 = Environment.replaceParams(caseInfo.getExpected());
        caseInfo.setExpected(params2);
        //替换url
        String params3 = Environment.replaceParams(caseInfo.getUrl());
        caseInfo.setUrl(params3);
        //替换请求头
        String params4 = Environment.replaceParams(caseInfo.getRequestHeader());
        caseInfo.setRequestHeader(params4);
        //替换前置sql
        String params5 = Environment.replaceParams(caseInfo.getBeforeSQL());
        caseInfo.setBeforeSQL(params5);
        //替换后置sql
        String params6 = Environment.replaceParams(caseInfo.getAfterSQL());
        caseInfo.setAfterSQL(params6);

    }

    /**
     * 获取sql执行的返回结果，数据格式：{SQLResult1:1,SQLResult2:"张三"}
     * @param type          before / after
     * @param sqls          sql集合
     * @return
     * @throws SQLException
     */
    public Map<String,Object> getSQLResults(String type, String sqls) throws SQLException {
        if(StringUtils.isNotBlank(sqls)) {
            //把sql数组转成 list集合
            List<String> sqlList = JSONObject.parseArray(sqls, String.class);
            Map<String, Object> sqlResults = new HashMap<>();
            int i = 1;
            for (String sql : sqlList) {
                //执行sql
                Object beforeSQLResult = JDBCUtils.singleResult(sql);       //0  王五
                sqlResults.put(type + "SQLResult" + i++, beforeSQLResult);
            }
            return sqlResults;
        }
        return null;
    }

}
