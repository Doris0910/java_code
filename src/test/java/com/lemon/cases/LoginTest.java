package com.lemon.cases;

import com.alibaba.fastjson.JSONObject;
import com.lemon.commons.BaseTest;
import com.lemon.commons.Environment;
import com.lemon.commons.ExcelUtils;
import com.lemon.pojo.CaseInfo ;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-25 20:14
 * @Desc：
 **/
public class LoginTest extends BaseTest {


    @BeforeClass
    public void beforeClass() {
        //1、加载前置用例信息
        System.out.println("-----------执行前置脚本--------------");
        List<CaseInfo> list = ExcelUtils.getExcelSheetPartData(2, 1, 1);
        CaseInfo caseInfo = list.get(0);
        //2、执行前置用例
        Response response = request(caseInfo);
        //提取参数存放到环境变量中
        extractParas2Env(caseInfo, response);
        System.out.println(Environment.envMap);
    }


    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        //1、参数化
        replaceParams(caseInfo);
        //2、发送请求
        Response response = request(caseInfo);
        //3、响应断言
        assertResponse(caseInfo, response);
    }


    @DataProvider
    public Object[] datas() {
        //读取第二个sheet,从第二行开始读到末尾
        List<CaseInfo> caseInfoList = ExcelUtils.getExcelSheetPartData(2, 2, 0);
        return caseInfoList.toArray();
    }

}
