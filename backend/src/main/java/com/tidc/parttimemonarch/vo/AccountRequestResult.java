package com.tidc.parttimemonarch.vo;


import org.springframework.stereotype.Component;


@Component
public class AccountRequestResult extends RequestResult {
    private Object accountInfo;

    public void succeed(Object accountInfo){
        this.succeed();
        this.accountInfo = accountInfo;
    }

    public Object getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Object accountInfo) {
        this.accountInfo = accountInfo;
    }
}
