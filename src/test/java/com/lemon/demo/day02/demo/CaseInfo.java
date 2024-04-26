package com.lemon.demo.day02.demo;

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

    private String mobilePhone;
    private String pwd;

    public CaseInfo(String mobilePhone, String pwd) {
        this.mobilePhone = mobilePhone;
        this.pwd = pwd;
    }

    public CaseInfo() {
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "CaseInfo{" +
                "mobilePhone='" + mobilePhone + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
