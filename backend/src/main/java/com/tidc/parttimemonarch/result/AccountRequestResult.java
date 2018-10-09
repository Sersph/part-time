package com.tidc.parttimemonarch.result;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.model.PersonalUser;

public class AccountRequestResult extends RequestResult {
    private PersonalUser personalUserInfo;

    public AccountRequestResult(Code code, PersonalUser personalUserInfo) {
        super(code);
        this.personalUserInfo = personalUserInfo;
    }

    public PersonalUser getPersonalUserInfo() {
        return personalUserInfo;
    }

    public void setPersonalUserInfo(PersonalUser personalUserInfo) {
        this.personalUserInfo = personalUserInfo;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + this.getCode() +
                "result=" + this.getMessage() +
                "userInfo=" + personalUserInfo +
                '}';
    }
}

