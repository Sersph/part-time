package com.tidc.parttimemonarch.vo;

import com.tidc.parttimemonarch.domain.UserInfo;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 22:08
 */
@Component
@Data
public class UserInfoRequestResult extends RequestResult {

    private UserInfo userInfo;

    public void succeed(UserInfo userInfo) {
        super.succeed();
        this.userInfo = userInfo;
    }
}
