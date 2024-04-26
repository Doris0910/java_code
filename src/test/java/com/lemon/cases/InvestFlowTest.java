package com.lemon.cases;

import com.lemon.commons.BaseTest;
import com.lemon.commons.Environment;
import com.lemon.commons.ExcelUtils;
import com.lemon.pojo.CaseInfo;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-29 20:46
 * @Desc：
 **/
public class InvestFlowTest extends BaseTest {

    @BeforeClass
    public void beforeClass() {

    }

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        //1、参数化
        replaceParams(caseInfo);
        //2、发送请求
        Response response = request(caseInfo);
        //3、响应断言
        assertResponse(caseInfo, response);
        //4、提取参数
        extractParas2Env(caseInfo, response);
    }


    @DataProvider
    public Object[] datas() {
        //读取第二个sheet,从第二行开始读到末尾
        List<CaseInfo> caseInfoList = ExcelUtils.getExcelSheetAllData(3);
        return caseInfoList.toArray();
    }
}
