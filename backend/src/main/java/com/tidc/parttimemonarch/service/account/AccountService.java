package com.tidc.parttimemonarch.service.account;

import com.tidc.parttimemonarch.dao.AccountDAO;
import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.result.AccountRequestResult;
import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.model.User;
import com.tidc.parttimemonarch.util.DateUtli;
import com.tidc.parttimemonarch.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Component
public class AccountService implements IAccountService {

    @Autowired
    private AccountDAO accountDAO;

    public RequestResult getUserInfo(HttpSession session){
        if (SessionUtil.isSession("user", session)){
            return new AccountRequestResult(Code.SUCCEED, (User)session.getAttribute("user"));
        }

        return new RequestResult(Code.USER_NOT_LOG_IN);
    }


    public RequestResult signIn(User user, HttpSession session){

        user = this.accountDAO.signIn(user);

        if (user == null){
            return new RequestResult(Code.INCORRECT_USERNAME_AND_PASSWORD);
        }

        this.accountDAO.updateLastSignInAt(user.getUsername(),DateUtli.getDate());

        SessionUtil.addSession("user", user, session);

        return new RequestResult(Code.SUCCEED);
    }




    /**
     * 使用用户名和密码进行注册
     * 注册成功后加入 session 设置为登陆状态
     * @param user
     * @return RequestResult 返回请求状态
     */
    public RequestResult useUsernameAndPasswordToSignUp(User user, HttpSession session){
        RequestResult requestResult;
        Date date = new Date(new java.util.Date().getTime());
        user.setLastSignInAt(date);
        user.setCreatedAt(date);
        user.setUpdatedAt(date);

        try{
            int matched = accountDAO.save(user);
            requestResult = new RequestResult(matched != 0 ? Code.SUCCEED : Code.ERROR);
            SessionUtil.addSession("user", user, session);
        }catch (DataAccessException e){
            requestResult = new RequestResult(Code.USER_NAME_ALREADY_EXIST);
        }

        return requestResult;
    }


    /**
     * 验证手机号进行注册
     * @param user
     * @return RequestResult 返回请求状态
     */
    public RequestResult verifyPhoneNumberSignUp(User user, HttpSession session){
        return null;
    }
}
