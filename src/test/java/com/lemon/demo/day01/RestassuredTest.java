package com.lemon.demo.day01;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

/**
* @Project: JAVA_33_32_31
* @Site: http://www.lemonban.com
* @Forum: http://testingpai.com
* @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
* @Author: luojie
* @Create: 2021-10-20 21:46
* @Desc：
**/
public class RestassuredTest {

    @Test
    public void test1() {
        String jsonData = "{\"member_id\": \"1236995562\",\"amount\": \"5678\"}";
        Response res = RestAssured
                .given()
                    .header("X-Lemonban-Media-Type", "lemonban.v1")
                    .header("Content-Type", "application/json")
                    .body(jsonData)
                .when()
                    .post("http://api.lemonban.com/futureloan/member/withdraw");
        System.out.println(res.asString());
    }


    @Test
    public void test2() {
        Response res = RestAssured
                .given()
                .header("X-Lemonban-Media-Type", "lemonban.v1")
                .when()
                .get("http://api.lemonban.com/futureloan/member/1236995562/info");
        System.out.println(res.asString());
    }

    @Test
    public void test3() {
        String jsonData = "{\"loan_id\": \"1\",\"approved_or_not\": true}";
        Response res = RestAssured
                .given()
                .header("X-Lemonban-Media-Type", "lemonban.v1")
                .header("Content-Type", "application/json")
                .body(jsonData)
                .when()
                .patch("http://api.lemonban.com/futureloan/loan/audit");
        System.out.println(res.asString());
    }

}
