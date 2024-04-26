package com.lemon.demo.day02.test;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-22 20:13
 * @Desc：
 **/
public class DDTDemo {


    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        Map map = JSONObject.parseObject(caseInfo.getRequestHeader(), Map.class);
        Response response = RestAssured
                .given()
                .headers(map)
                .body(caseInfo.getInputParams())
                .when()
                .post("http://api.lemonban.com/futureloan" + caseInfo.getUrl());

        //?如何实现断言
        //jsonpath  gpath    code  msg  data.reg_name   data.mobile_phone
        //实际值gpath表达式 和 期望值成对出现
        //{code : 0}
        //用gpath通过实际值gpath表达式 取出 实际值。
        //通过实际值gpath表达式做为key 取出 期望值。
        //取出来实际值 期望值比较
        System.out.println(response.asString());
        //期望值 从哪来？  excel
        Map<String,Object> expectedMap = JSONObject.parseObject(caseInfo.getExpected(), Map.class);
        //遍历Map
        expectedMap.forEach((gPath,expectedValue) -> {
            //key：找到实际值的表达式
            //value：期望值
            Object actual = response.jsonPath().get(gPath);
            Assert.assertEquals(actual,expectedValue);
            System.out.println("actual:" + actual);
            System.out.println("expectedValue:" + expectedValue);
        });
    }

    @DataProvider
    public Object[] datas() {
        List<CaseInfo> caseInfoList = getExcelData();
        return caseInfoList.toArray();
    }

    /**
     * 返回excel中第一个sheet的所有数据
     * @return
     */
    public static List<CaseInfo> getExcelData() {
        List<CaseInfo> caseInfoList = null;
        try {
            //1、加载excel
            FileInputStream fis = new FileInputStream("src/test/resources/api_testcases_futureloan_v1.xls");
            //2、导入参数（excel数据导入java代码）
            ImportParams importParams = new ImportParams();
            //设置sheet开始索引
            importParams.setStartSheetIndex(0);
            //3、加载数据
            //importExcel(execl文件，映射类.class,导入参数对象)
            caseInfoList = ExcelImportUtil.importExcel(fis, CaseInfo.class, importParams);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return caseInfoList;
    }

}
