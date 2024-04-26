package com.lemon.commons;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-27 20:17
 * @Desc：
 **/
public class Environment {
    //Map
    public static Map<String,Object> envMap = new HashMap<>();

    /**
     * 参数化方法
     * 参数化方式一：遍历环境变量，取出所有key拼接{{}}，最后替换。
     * 缺点：当环境变量比较多，相对损耗资源（出现很多不需要匹配的key）。
     * 参数化二：使用正则表达式取出带有{{}}参数，弥补方式一缺点。
     * @param params        需要替换的字符串
     * @return              替换之后的字符
     */
    public static String replaceParams(String params) {
        //1、参数化方式一：遍历环境变量，取出所有key拼接{{}}，最后替换。
        //缺点：当环境变量比较多，相对损耗资源（出现很多不需要匹配的key）。
//        Set<String> keySet = envMap.keySet();
//        for (String key : keySet) {
//            Object value = envMap.get(key);
//            String key2 = "{{" + key + "}}";
//            str = str.replace(key2, value.toString());
//        }
//        System.out.println(str);
        //2、参数化二：弥补方式一缺点。
        if(StringUtils.isNotBlank(params)) {
            String regex = "\\{\\{(.+?)\\}\\}";
            Pattern pattern = Pattern.compile(regex);
            //匹配对象
            Matcher matcher = pattern.matcher(params);
            while (matcher.find()) {
                //查询到匹配字符串(不带括号{{mobile_phone}})
                String findStr = matcher.group(0);
                //匹配字符串中第一个分组的内容(不带大括号mobile_phone)
                String groupOneStr = matcher.group(1);
                //从环境变量中取出对应值
                Object actual = envMap.get(groupOneStr);
                params = params.replace(findStr, actual + "");
            }
        }
        return params;
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "  \"mobile_phone\":\"{{mobile_phone}}\",\n" +
                "  \"pwd\": \"12345678\",\n" +
                "  \"id\":\"{{member_id}}\"\n" +
                "}";

        envMap.put("member_id","1237008423");
        envMap.put("mobile_phone","13345454548");
        envMap.put("token","13345454548");
        envMap.put("asdasd","13345454548");
        envMap.put("123123","13345454548");
        //1、参数化方式一：遍历环境变量，取出所有key拼接{{}}，最后替换。
        //缺点：当环境变量比较多，相对损耗资源（出现很多不需要匹配的key）。
//        Set<String> keySet = envMap.keySet();
//        for (String key : keySet) {
//            Object value = envMap.get(key);
//            String key2 = "{{" + key + "}}";
//            str = str.replace(key2, value.toString());
//        }
//        System.out.println(str);
        //2、参数化二：弥补方式一缺点。
        String regex = "\\{\\{(.+?)\\}\\}";
        Pattern pattern = Pattern.compile(regex);
        //匹配对象
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()) {
            //查询到匹配字符串(不带括号{{mobile_phone}})
            String findStr = matcher.group(0);
            //匹配字符串中第一个分组的内容(不带大括号mobile_phone)
            String groupOneStr = matcher.group(1);
            //从环境变量中取出对应值
            Object actual = envMap.get(groupOneStr);
            str = str.replace(findStr, actual.toString());
        }
        System.out.println(str);
    }


}
