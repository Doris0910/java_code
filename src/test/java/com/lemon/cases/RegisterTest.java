package com.lemon.cases;

import com.alibaba.fastjson.JSONObject;
import com.lemon.commons.*;
import com.lemon.pojo.CaseInfo;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
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
public class RegisterTest extends BaseTest {


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
        Map<String, Object> before = getSQLResults("before", caseInfo.getBeforeSQL());
        //发送请求
        Response response = request(caseInfo);
        //执行后置sql脚本
        Map<String, Object> after = getSQLResults("after", caseInfo.getAfterSQL());
        //响应断言
        assertResponse(caseInfo, response);


        //数据库断言 （业务耦合，换项目必须重写）
        if(StringUtils.isNotBlank(caseInfo.getAfterSQL())) {
            Object beforeSQLResult1 = before.get("beforeSQLResult1");
            Object afterSQLResult1 = after.get("afterSQLResult1");
            //注册之前sql结果等于0，注册之后slq结果1
            if (!((Long) beforeSQLResult1 == 0 && (Long) afterSQLResult1 == 1)) {
                Assert.fail("数据库断言失败：beforeSQLResult1:" + beforeSQLResult1 + ",afterSQLResult1:" + afterSQLResult1);
            }
        }
    }



    @DataProvider
    public Object[] datas() {
        //读取第一个sheet
        List<CaseInfo> caseInfoList = ExcelUtils.getExcelSheetAllData(1);
        return caseInfoList.toArray();
    }


}
