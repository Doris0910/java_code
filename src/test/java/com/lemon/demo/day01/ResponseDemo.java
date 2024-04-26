package com.lemon.demo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-20 21:07
 * @Desc：
 **/
public class ResponseDemo {

    @Test
    public void getResponse() {
        Response response = RestAssured
                .given()
                    .cookie("sessionId","123")
                .when()
                .get("http://httpbin.org/get?id=1");
        //打印响应体
        System.out.println(response.asString());
        //打印所有响应头
        System.out.println(response.getHeaders());
        //打印所有cookie
        System.out.println(response.getCookies());
        //响应时长
        System.out.println(response.time());
        //状态码
        System.out.println(response.statusCode());
    }


    @Test
    public void gPath() {
//        Response response = RestAssured
//                .get("http://httpbin.org/json");
//        System.out.println(response.asString());
////        System.out.println(response.jsonPath().get());
//        System.out.println(response.jsonPath().get("slideshow.author"));
//        System.out.println(response.jsonPath().get("slideshow.slides[0]"));
//        System.out.println(response.jsonPath().get("slideshow.slides[0].title"));


//        String jsonData = "{\"mobile_phone\": \"18912312323\",\"pwd\": \"12345678\"}";
//        Response res = RestAssured
//                .given()
//                .header("X-Lemonban-Media-Type", "lemonban.v2")
//                .header("Content-Type", "application/json")
//                .body(jsonData)
//                .when()
//                .post("http://api.lemonban.com/futureloan/member/login");
//        res.jsonPath().get("data.token_info.token");


//        Response res = RestAssured
//                .get("http://httpbin.org/html");
//        System.out.println(res.asString());
//        System.out.println(res.htmlPath().get("html.body.h1"));


        Response res = RestAssured
                .get("http://httpbin.org/xml");
        System.out.println(res.asString());
//        System.out.println(res.xmlPath().get("slideshow.slide.@type"));
//        System.out.println(res.xmlPath().get("slideshow.slide[0].'@type'"));
    }
}
