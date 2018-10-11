package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.dao.AccountDAO;
import com.tidc.parttimemonarch.exceptions.ResultExceptions;
import com.tidc.parttimemonarch.pojo.EnterpriseUser;
import com.tidc.parttimemonarch.pojo.PersonalUser;
import com.tidc.parttimemonarch.service.AccountService;
import com.tidc.parttimemonarch.util.DateUtil;
import com.tidc.parttimemonarch.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class AccountServiceImpl implements AccountService {


    //持久层操作接口
    @Autowired
    private AccountDAO accountDAO;


    //redis 操作对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;






    /**
     *  企业用户登陆，注册
     * @Author: 李家宝
     */

    /**
     * 企业用户注册
     * @param enterpriseUser
     * @param captcha
     * @return
     */
    @Override
    public EnterpriseUser enterpriseSignUp(EnterpriseUser enterpriseUser, String captcha) {

        String redisCaptcha = (String) RedisUtil.getValueByKey(enterpriseUser.getEmail() + "code", this.stringRedisTemplate);

        if (!redisCaptcha.equals(captcha)){
            throw new ResultExceptions(1006, "验证码错误");
        }

        enterpriseUser.setDate();

        try{
            int matched = accountDAO.addEnterpriseUser(enterpriseUser);
            if (matched == 0){
                throw new ResultExceptions(0, "未知错误");
            }

        }catch (DataAccessException e){
            e.printStackTrace();
            throw new ResultExceptions(1001 , "此邮箱已被注册");
        }

        return enterpriseUser;
    }


    /**
     * 企业用户登陆
     * @param enterpriseUser
     * @return
     */
    @Override
    public EnterpriseUser enterpriseSignIn(EnterpriseUser enterpriseUser) {

        enterpriseUser = this.accountDAO.enterpriseSignIn(enterpriseUser.getEmail(), enterpriseUser.getPassword());

        if (enterpriseUser == null){
            throw new ResultExceptions(1003, "邮箱，密码不正确");
        }

        this.accountDAO.updateLastSignInAtInEnterprise(enterpriseUser.getEmail(), DateUtil.getDate());

        return enterpriseUser;
    }

















    /**
     *
     *  普通用户登陆，注册
     *  
     * @Author: 李家宝
    */





    /**
     * 普通用户登陆
     * @param personalUser
     */
    public PersonalUser personalSignIn(PersonalUser personalUser){

        this.accountDAO.updateLastSignInAtInPersonal(personalUser.getUsername(),DateUtil.getDate());

        personalUser = this.accountDAO.signIn(personalUser);

        if (personalUser == null){
            throw new ResultExceptions(1003, "用户名，密码不正确");
        }

        this.accountDAO.updateLastSignInAtInPersonal(personalUser.getUsername(),DateUtil.getDate());

        return personalUser;
    }




    /**
     * 普通用户名注册
     * @param personalUser
     * @return PersonalUser 对象
     */
    public PersonalUser personalSignUp(PersonalUser personalUser){

        personalUser.setDate();

        try{
            int matched = accountDAO.addPersonalUser(personalUser);
            if (matched == 0){
                throw new ResultExceptions(0, "未知错误");
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            throw new ResultExceptions(1001 , "用户名已存在");
        }

        return personalUser;
    }
}
