package com.lemon.demo.day02.demo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

//    @Test(dataProvider = "datas")
//    public void test(String mobilePhone,String pwd) {
//        String jsonData = "{\"mobile_phone\": \""+mobilePhone+"\",\"pwd\": \""+pwd+"\"}";
//        System.out.println(jsonData);
////        Response res = RestAssured
////                .given()
////                .header("X-Lemonban-Media-Type", "lemonban.v2")
////                .header("Content-Type", "application/json")
////                .body(jsonData)
////                .when()
////                .post("http://api.lemonban.com/futureloan/member/login");
//    }

//    @DataProvider
//    public Object[][] datas() {
//        Object[][] datas = {
//                {"18912312323","12345678"},
//                {"18912312324","12345678"}
//        };
//        return datas;
//    }

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        String jsonData = "{\"mobile_phone\": \""+caseInfo.getMobilePhone()+"\",\"pwd\": \""+caseInfo.getPwd()+"\"}";
        System.out.println(jsonData);
//        Response res = RestAssured
//                .given()
//                .header("X-Lemonban-Media-Type", "lemonban.v2")
//                .header("Content-Type", "application/json")
//                .body(jsonData)
//                .when()
//                .post("http://api.lemonban.com/futureloan/member/login");
    }

    @DataProvider
    public Object[] datas() {
        CaseInfo c1 = new CaseInfo("18912312323","12345678");
        CaseInfo c2 = new CaseInfo("18912312324","12345678");
        Object[] datas = {c1,c2};
//        int[] arr = {1,2,3,4};
        return datas;
    }



}
