package com.yyccyy.j.authenticationserver.service.impl;

import com.yyccyy.j.authenticationserver.mapper.UserMapper;
import com.yyccyy.j.authenticationserver.pojo.User;
import com.yyccyy.j.authenticationserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张朝锋
 * @author 张伟杰
 * @since 2018-09-14
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean checkPassword(String username, String password) {
        User user = userMapper.selectUserByUsername(username);
        return user != null && password.equals(user.getPassword());
    }

    @Override
    public boolean alterPassword(String username, String newPassword) {
        if (null == userMapper.selectUserByUsername(username)){
            return false;
        }

        return userMapper.updatePasswordByUsername(newPassword, username) == 1 ? true : false;
    }
}
