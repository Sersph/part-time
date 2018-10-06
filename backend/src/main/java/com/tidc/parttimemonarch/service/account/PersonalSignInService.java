package com.tidc.parttimemonarch.service.account;

import com.tidc.parttimemonarch.dao.AccountDAO;
import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.message.AccountRequestState;
import com.tidc.parttimemonarch.message.RequestState;
import com.tidc.parttimemonarch.model.User;
import com.tidc.parttimemonarch.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class PersonalSignInService {

    @Autowired
    private AccountDAO accountDAO;

    /**
     * 用户名密码登陆
     * @param username
     * @param password
     * @param session
     * @return 返回状态
     */
    public RequestState signIn(String username, String password, HttpSession session){

        Code code = UserUtil.validateUsernameAndPassword(username, password);


        if (CodeUtli.isSuccceed(code)){
            return new RequestState(code);
        }

        User user = this.accountDAO.findByUsername(username);

        if (user == null){
            return new RequestState(Code.THE_USER_DOES_NOT_EXIST);
        }

        if (password.equals(user.getPassword())){
            if (SessionUtil.addSession(user, session)){
                user.setLastSignInAt(DateUtli.getDate());
                this.accountDAO.save(user);
                return new AccountRequestState(Code.SUCCEED, user);
            }
        }

        return new RequestState(Code.INCORRECT_USERNAME_AND_PASSWORD);
    }
}
