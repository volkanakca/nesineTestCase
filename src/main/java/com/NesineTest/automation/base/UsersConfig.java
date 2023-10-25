package com.NesineTest.automation.base;


import com.NesineTest.automation.driver.PageGenarator;

public class UsersConfig extends PageGenarator {

    private String member;
    private String password;

    public String getPassword(String userName) {
        return password = getUser("password", userName);
    }


    public String getMember(String userName) {
        return member = getUser("member", userName);
    }

    public void setMember(String msisdn) {
        this.member = msisdn;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
