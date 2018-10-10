package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.dao.AccountDAO;
import com.tidc.parttimemonarch.exceptions.ResultExceptions;
import com.tidc.parttimemonarch.model.PersonalUser;
import com.tidc.parttimemonarch.service.AccountService;
import com.tidc.parttimemonarch.util.DateUtil;
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

    public Object getUserInfo(HttpSession session){

        if (!SessionUtil.isSession("user", session)){
            throw new ResultExceptions(1003, "用户未登陆" );
        }

        return session.getAttribute("user");
    }


    /**
     * 普通用户登陆
     * @param personalUser
     * @param session
     */
    public void personalSignUp(PersonalUser personalUser, HttpSession session){
        //对密码进行md5加密
        this.accountDAO.updateLastSignInAt(personalUser.getUsername(),DateUtil.getDate());

        personalUser = this.accountDAO.signIn(personalUser);

        if (personalUser == null){
            throw new ResultExceptions(1003, "用户名密码不正确");
        }

        this.accountDAO.updateLastSignInAt(personalUser.getUsername(),DateUtil.getDate());

        SessionUtil.addSession("user", personalUser, session);
    }




    /**
     * 使用用户名和密码进行注册
     * 注册成功后加入 session 设置为登陆状态
     * @param personalUser
     * @return RequestResult 返回请求状态
     */
    public void personalSignIn(PersonalUser personalUser, HttpSession session){

        Date date = new Date(new java.util.Date().getTime());
        personalUser.setLastSignInAt(date);
        personalUser.setCreatedAt(date);
        personalUser.setUpdatedAt(date);
        this.accountDAO.updateLastSignInAt(personalUser.getUsername(),DateUtil.getDate());

        try{
            int matched = accountDAO.save(personalUser);

            if (matched == 0){
                throw new ResultExceptions(0, "未知错误");
            }

            SessionUtil.addSession("user", personalUser, session);

        }catch (DataAccessException e){
            throw new ResultExceptions(1001 , "用户名已存在");
        }

    }
}
