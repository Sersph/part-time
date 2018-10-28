package com.tidc.parttimemonarch.vo;


import com.tidc.parttimemonarch.domain.UserInfo;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class UserInfoRequestResult extends RequestResult {
    private UserInfo userInfo;

    public void succeed(UserInfo accountInfo){
        this.succeed();
        this.userInfo = userInfo;
    }
}
