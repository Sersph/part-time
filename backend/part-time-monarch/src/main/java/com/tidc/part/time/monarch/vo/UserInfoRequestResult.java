package com.tidc.part.time.monarch.vo;

import com.tidc.part.time.monarch.domain.UserInfo;
import lombok.Data;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 22:08
 */
@Data
public class UserInfoRequestResult extends RequestResult {

    private UserInfo userInfo;

    public RequestResult succeed(UserInfo userInfo) {
        super.succeed();
        this.userInfo = userInfo;
        return this;
    }
}
