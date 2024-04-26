package com.lemon.commons;

import com.github.javafaker.Address;
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
 * @Create: 2021-11-01 20:18
 * @Desc：
 **/
public class RandomUtils {

    static Faker faker = new Faker(Locale.CHINA);

    /**
     * 随机生成11位手机号码
     * @return
     */
    public static String getPhone() {
        PhoneNumber phoneNumber = faker.phoneNumber();
        while(true) {
            String phone = phoneNumber.cellPhone();
            if(!phone.startsWith("17")){
                return phone;
            }
        }
    }

    /**
     * 随机生成城市名
     * @return
     */
    public static String getAddress() {
        Address address = faker.address();
        return address.cityName();
    }

    /**
     * 随机生成姓名
     * @return
     */
    public static String getName() {
        Name name = faker.name();
        return name.fullName();
    }

}
