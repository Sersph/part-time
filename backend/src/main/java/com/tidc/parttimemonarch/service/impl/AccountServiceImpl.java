package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.dao.AccountDAO;
import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.result.AccountRequestResult;
import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.model.PersonalUser;
import com.tidc.parttimemonarch.service.AccountService;
import com.tidc.parttimemonarch.util.DateUtli;
import com.tidc.parttimemonarch.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public RequestResult getUserInfo(HttpSession session){
        if (SessionUtil.isSession("user", session)){
            return new AccountRequestResult(Code.SUCCEED, (PersonalUser)session.getAttribute("user"));
        }

        return new RequestResult(Code.USER_NOT_LOG_IN);
    }




    public RequestResult personalSignUp(PersonalUser personalUser, HttpSession session){

        personalUser = this.accountDAO.signIn(personalUser);

        if (personalUser == null){
            return new RequestResult(Code.INCORRECT_USERNAME_AND_PASSWORD);
        }

        this.accountDAO.updateLastSignInAt(personalUser.getUsername(),DateUtli.getDate());

        SessionUtil.addSession("user", personalUser, session);

        return new RequestResult(Code.SUCCEED);
    }




    /**
     * 使用用户名和密码进行注册
     * 注册成功后加入 session 设置为登陆状态
     * @param personalUser
     * @return RequestResult 返回请求状态
     */
    public RequestResult personalSignIn(PersonalUser personalUser, HttpSession session){
        RequestResult requestResult;
        Date date = new Date(new java.util.Date().getTime());
        personalUser.setLastSignInAt(date);
        personalUser.setCreatedAt(date);
        personalUser.setUpdatedAt(date);

        try{
            int matched = accountDAO.save(personalUser);
            requestResult = new RequestResult(matched != 0 ? Code.SUCCEED : Code.ERROR);
            SessionUtil.addSession("user", personalUser, session);
        }catch (DataAccessException e){
            requestResult = new RequestResult(Code.USER_NAME_ALREADY_EXIST);
        }

        return requestResult;
    }
}
