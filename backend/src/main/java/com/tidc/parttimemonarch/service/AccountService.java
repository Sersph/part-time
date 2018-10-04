package com.tidc.parttimemonarch.service;

import com.tidc.parttimemonarch.dao.AccountDAO;
import com.tidc.parttimemonarch.enumerate.AccountCode;
import com.tidc.parttimemonarch.message.AccountRequestState;
import com.tidc.parttimemonarch.message.RequestState;
import com.tidc.parttimemonarch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public RequestState useUsernameAndPasswordToSignUp(User user){
        RequestState requestState;

        try{
            requestState = new AccountRequestState(AccountCode.SUCCEED, accountDAO.save(user));
        }catch (DataAccessException e){
            requestState = new RequestState(AccountCode.USER_NAME_ALREADY_EXIST);
        }

        return requestState;
    }

}
