package com.lemon.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @Project: JAVA_33_32_31
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright: ©2021 版权所有 湖南省零檬信息技术有限公司
 * @Author: luojie
 * @Create: 2021-10-22 20:19
 * @Desc：
 **/
public class CaseInfo {
    //easypoi 映射类 必须 私有属性、空参构造、get/set
    @Excel(name = "序号(caseId)")
    private Integer caseId;
    @Excel(name = "接口模块(interface)")
    private String module;
    @Excel(name = "用例标题(title)")
    private String title;
    @Excel(name = "请求头(requestHeader)")
    private String requestHeader;
    @Excel(name = "请求方式(method)")
    private String method;
    @Excel(name = "接口地址(url)")
    private String url;
    @Excel(name = "参数输入(inputParams)")
    private String inputParams;
    @Excel(name = "期望返回结果(expected)")
    private String expected;
    @Excel(name = "提取表达式(extractExper)")
    private String extractExper;
    @Excel(name = "前置sql脚本(beforeSQL)")
    private String beforeSQL;
    @Excel(name = "后置sql脚本(afterSQL)")
    private String afterSQL;




    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInputParams() {
        return inputParams;
    }

    public void setInputParams(String inputParams) {
        this.inputParams = inputParams;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getExtractExper() {
        return extractExper;
    }

    public void setExtractExper(String extractExper) {
        this.extractExper = extractExper;
    }

    public String getBeforeSQL() {
        return beforeSQL;
    }

    public void setBeforeSQL(String beforeSQL) {
        this.beforeSQL = beforeSQL;
    }

    public String getAfterSQL() {
        return afterSQL;
    }

    public void setAfterSQL(String afterSQL) {
        this.afterSQL = afterSQL;
    }

    @Override
    public String toString() {
        return "CaseInfo{" +
                "caseId=" + caseId +
                ", module='" + module + '\'' +
                ", title='" + title + '\'' +
                ", requestHeader='" + requestHeader + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", inputParams='" + inputParams + '\'' +
                ", expected='" + expected + '\'' +
                ", extractExper='" + extractExper + '\'' +
                ", beforeSQL='" + beforeSQL + '\'' +
                ", afterSQL='" + afterSQL + '\'' +
                '}';
    }
}
