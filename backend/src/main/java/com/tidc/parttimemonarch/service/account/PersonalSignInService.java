package com.tidc.parttimemonarch.service.account;

import com.tidc.parttimemonarch.dao.AccountDAO;
import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.message.AccountRequestState;
import com.tidc.parttimemonarch.message.RequestState;
import com.tidc.parttimemonarch.model.User;
import com.tidc.parttimemonarch.util.DateUtli;
import com.tidc.parttimemonarch.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class PersonalSignInService {

    @Autowired
    private AccountDAO accountDAO;

    /**
     * 用户名密码登陆
     * @param session
     * @return 返回状态
     */
    public RequestState signIn(User user, HttpSession session){

        user = this.accountDAO.signIn(user);

        if (user == null){
            return new RequestState(Code.INCORRECT_USERNAME_AND_PASSWORD);
        }

        this.accountDAO.updateLastSignInAt(user.getUsername(),DateUtli.getDate());

        SessionUtil.addSession("user", user, session);

        return new RequestState(Code.SUCCEED);
    }
}
