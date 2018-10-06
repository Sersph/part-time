package com.tidc.parttimemonarch.service.account;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.message.AccountRequestState;
import com.tidc.parttimemonarch.message.RequestState;
import com.tidc.parttimemonarch.model.User;
import com.tidc.parttimemonarch.util.SessionUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class AccountStateService {

    public RequestState isTheUserLoggedIn(HttpSession session){
        if (SessionUtil.islogIn(session)){
            return new AccountRequestState(Code.USER_LOGGER_IN, (User)session.getAttribute(SessionUtil.SESSION_KEY));
        }

        return new RequestState(Code.USER_NOT_LOG_IN);
    }
}
