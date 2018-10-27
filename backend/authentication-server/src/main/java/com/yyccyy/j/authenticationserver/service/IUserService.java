package com.yyccyy.j.authenticationserver.service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张朝锋
 * @author 张伟杰
 * @since 2018-09-14
 */
public interface IUserService {

    /**
     * 通过提交的用户名密码和后台验证
     * @param userId 用户Id，即用户的账户
     * @param password 用户密码
     * @return 校验结果
     */
    boolean checkPassword(String userId, String password);

    boolean alterPassword(String userId, String newPassword);
}
