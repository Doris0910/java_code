package com.lemon.cases;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lemon.commons.BaseTest;
import com.lemon.commons.Environment;
import com.lemon.commons.ExcelUtils;
import com.lemon.commons.RandomUtils;
import com.lemon.pojo.CaseInfo;
import groovy.json.JsonParser;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-25 20:13
 * @Desc：
 **/
public class RechargeTest extends BaseTest {


    @BeforeClass
    public void beforeClass() {
        //1、获取随机手机号码
        String phone = RandomUtils.getPhone();
        //2、把手机号码存放到环境变量中。
        Environment.envMap.put("mobile_phone", phone);
    }

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) throws Exception {
        //参数化
        replaceParams(caseInfo);
        //执行前置sql脚本
//        Map<String, Object> before = getSQLResults("before", caseInfo.getBeforeSQL());
        //发送请求
        Response response = request(caseInfo);
        //执行后置sql脚本
//        Map<String, Object> after = getSQLResults("after", caseInfo.getAfterSQL());
        //响应断言
        assertResponse(caseInfo, response);
        //提出参数
        extractParas2Env(caseInfo, response);
        //数据库断言 （业务耦合，换项目必须重写）
        //取出请求参数中 amount
//        if(StringUtils.isNotBlank(caseInfo.getAfterSQL())) {
//            //这里采用的是JsonPath需要加$.， 如果是GPATH就不用加。
//            BigDecimal amount = (BigDecimal)JSONPath.read(caseInfo.getInputParams(), "$.amount");
//            BigDecimal beforeSQLResult1 = (BigDecimal) before.get("beforeSQLResult1");
//            BigDecimal afterSQLResult1 = (BigDecimal) after.get("afterSQLResult1");
//            //充值后 -充值前 == amount（充值金额）
//            BigDecimal subtract = afterSQLResult1.subtract(beforeSQLResult1);
//            //compareTo 返回 0 说明相等  -1 小于 1 大于
//            if(subtract.compareTo(amount) != 0) {
//                Assert.fail("充值断言失败：充值金额：" + amount+",充值前的金额："+beforeSQLResult1+"充值后的金额："+ afterSQLResult1);
//            }
//            System.out.println("充值金额：" + amount+",充值前的金额："+beforeSQLResult1+"充值后的金额："+ afterSQLResult1);
//        }
    }



    @DataProvider
    public Object[] datas() {
        //读取第一个sheet
        List<CaseInfo> caseInfoList = ExcelUtils.getExcelSheetAllData(4);
        return caseInfoList.toArray();
    }


}
