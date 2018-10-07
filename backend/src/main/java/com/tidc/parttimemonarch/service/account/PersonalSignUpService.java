package com.tidc.parttimemonarch.service.account;

import com.tidc.parttimemonarch.dao.AccountDAO;
import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.message.RequestState;
import com.tidc.parttimemonarch.model.User;
import com.tidc.parttimemonarch.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Service
public class PersonalSignUpService {

    @Autowired
    private AccountDAO accountDAO;


    //划分注册业务类型
    public RequestState signUp(User user, HttpSession session) {

        if (user.getType() == 1){
            return this.useUsernameAndPasswordToSignUp(user, session);
        }else if(user.getType() == 2){
            return this.verifyPhoneNumberSignUp(user, session);
        }else{
            //找不到注册类型返回错误信息
            return new RequestState(Code.REGISTRATION_TYPE_CANNOT_BE_FOUND);
        }
    }

    /**
     * 使用用户名和密码进行注册
     * 注册成功后加入 session 设置为登陆状态
     * @param user
     * @return RequestState 返回请求状态
     */
    private RequestState useUsernameAndPasswordToSignUp(User user, HttpSession session){
        RequestState requestState;
        Date date = new Date(new java.util.Date().getTime());
        user.setLastSignInAt(date);
        user.setCreatedAt(date);
        user.setUpdatedAt(date);

        try{
            int matched = accountDAO.save(user);
            requestState = new RequestState(matched != 0 ? Code.SUCCEED : Code.ERROR);
            SessionUtil.addSession("user", user, session);
        }catch (DataAccessException e){
            requestState = new RequestState(Code.USER_NAME_ALREADY_EXIST);
        }

        return requestState;
    }


    /**
     * 验证手机号进行注册
     * @param user
     * @return RequestState 返回请求状态
     */
    private RequestState verifyPhoneNumberSignUp(User user, HttpSession session){
        return null;
    }
}
