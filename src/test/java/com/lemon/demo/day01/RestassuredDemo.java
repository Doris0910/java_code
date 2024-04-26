package com.lemon.demo.day01;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

import java.io.File;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-20 20:16
 * @Desc：
 **/
public class RestassuredDemo {

    @Test
    public void getRequest() {
//        get();
        RestAssured
                .given()
                    .header("X-Lemonban-Media-Type","lemonban.v1")
                    .queryParam("pageIndex","1")
                    .queryParam("pageSize","3")
                .when()
                    .get("http://api.lemonban.com/futureloan/loans")
                .then()
                    .log().body();

    }

    @Test
    public void postRequest() {
//        get();
//        String jsonData = "{\"mobile_phone\": \"13999888819\",\"pwd\": \"123456\"}";
//        RestAssured
//                .given()
//                    .header("X-Lemonban-Media-Type","lemonban.v1")
//                    .contentType("application/json")
//                    .body(jsonData)
//                .when()
//                    .post("http://api.lemonban.com/futureloan/member/login")
//                .then()
//                    .log().body();

        String jsonData = "{\"mobile_phone\": \"13999888819\",\"pwd\": \"123456\"}";
        RestAssured
                .given()
                    .header("X-Lemonban-Media-Type","lemonban.v1")
                    .header("Content-Type","application/json")
                    .body(jsonData)
                .when()
                    .post("http://api.lemonban.com/futureloan/member/login")
                .then()
                    .log().body();

    }

    @Test
    public void postFormRequest() {
        RestAssured
                .given()
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .body("username=zhangsan&password=123456")
                .when()
                    .post("http://httpbin.org/post")
                .then()
                    .log().body();
    }

    @Test
    public void postXmlRequest() {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<suite>\n" +
                "    <class>测试xml</class>\n" +
                "</suite>";

        RestAssured
                .given()
                    .header("Content-Type","application/xml")
                    .body(xmlStr)
                .when()
                    .post("http://httpbin.org/post")
                .then()
                    .log().body();
    }

    @Test
    public void postUploadRequest() {
        RestAssured
                .given()
                    .header("Content-Type","multipart/form-data")
                    .multiPart(new File("D:\\截图.png"))
                .when()
                    .post("http://httpbin.org/post")
                .then()
                    .log().body();
    }



}
