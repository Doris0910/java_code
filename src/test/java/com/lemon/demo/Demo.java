package com.lemon.demo;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.PhoneNumber;

import java.util.Locale;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-11-01 20:14
 * @Desc：
 **/
public class Demo {
    public static void main(String[] args) {
        Faker faker = new Faker(Locale.CHINA);
        PhoneNumber phoneNumber = faker.phoneNumber();
        System.out.println(phoneNumber.cellPhone());
        System.out.println(phoneNumber.cellPhone());
        System.out.println(phoneNumber.cellPhone());
    }
}
