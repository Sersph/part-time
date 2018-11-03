package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.domain.UserRole;
import com.tidc.parttimemonarch.exception.ResultException;
import com.tidc.parttimemonarch.domain.User;
import com.tidc.parttimemonarch.domain.UserInfo;
import com.tidc.parttimemonarch.mapper.UserInfoMapper;
import com.tidc.parttimemonarch.mapper.UserMapper;
import com.tidc.parttimemonarch.mapper.UserRoleMapper;
import com.tidc.parttimemonarch.security.UserTokenDTO;
import com.tidc.parttimemonarch.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
public class AccountServiceImpl implements AccountService {

    /**
     * 认证服务器认证接口
     */
    @Value("${ResourceServerConfiguration.oauthTokenUrl}")
    private String oauthTokenUrl;

    /**
     * 客户端账户Id
     */
    @Value("${ResourceServerConfiguration.clientId}")
    private String clientId;

    /**
     * 客户端秘文
     */
    @Value("${ResourceServerConfiguration.clientSecret}")
    private String clientSecret;

    @Value("${ResourceServerConfiguration.logoutUrl}")
    private String logoutUrl;

    // redis 操作对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // spring对web请求的封装类
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * redis 模板
     */
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    // 密码加密
    private static final String ENCODINGID = "{bcrypt}";
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;






    /**
     *
     * 用户登陆
     * 所有用户统一登陆接口
     * 该方法将用户数据转发到认证服务器获取到 token
     * 然后将 token 作为 key user 作为 value 存入 redis
     * 获取用户信息的时候前端传入 token 获取到 userId
     * 拿 userId 到数据库查询用户 类型
     * 再拿 userId 到对应类型的 userInfo 表中获取用户信息
     *
     * @param user
     * @return
     */
    public UserTokenDTO signIn(User user) {

        UserTokenDTO userTokenDTO;

        try {
            user.setId(this.userMapper.selectUserIdByUsername(user.getUsername()));
        }catch (Exception e){
            throw new ResultException(1005, "用户名或密码错误");
        }

        userTokenDTO = this.sendLoginData(user);

        return userTokenDTO;
    }


    /**
     *
     * 根据 token 获取用户数据
     *
     * @param token
     * @return
     */
    public UserInfo getAccountInfo(String token){
        User user = null;
        try {
            user = (User) this.redisTemplate.opsForValue().get(token);
        }catch (Exception e){
            e.printStackTrace();
        }

        return this.userInfoMapper.selectUserInfoByUserId(user.getId());
    }



    /**
     * 企业用户注册
     * @param captcha
     * @return
     */
    @Override
    public void enterpriseSignUp(UserInfo userInfo, User user, String captcha) {

        if (captcha == null){
            throw new ResultException(1002, "验证码不能为空");
        }


        if (this.redisTemplate.opsForValue().get(userInfo.getEmail()) == null){
            throw new ResultException(1002, "请先获取验证码");
        }

        if (!captcha.equals(this.redisTemplate.opsForValue().get(userInfo.getEmail()))){
            throw new ResultException(1005, "验证码不正确");
        }

        user.setPassword(ENCODINGID + bCryptPasswordEncoder.encode(user.getPassword()));
        userInfo.setDate(new Date());
        // 企业用户 角色id
        int roleId = 2;

        // 将用户保存到数据库
        this.addUser(user, userInfo, roleId);



        // 删除 redis 里面的验证码
        this.redisTemplate.delete(userInfo.getEmail());

    }

    /**
     * 普通用户注册
     * @param user
     * @return PersonalUser 对象
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void personalSignUp(User user){

        user.setPassword(ENCODINGID + bCryptPasswordEncoder.encode(user.getPassword()));

        UserInfo userInfo = new UserInfo();
        userInfo.setName(user.getUsername());
        userInfo.setDate(new Date());

        // 普通用户 角色id
        int roleId = 3;

        // 将用户保存到数据库
        this.addUser(user, userInfo, roleId);
    }



    @Override
    public void logout(String token) {
        restTemplate.getForObject(logoutUrl + "?token=" + token, Object.class);
        redisTemplate.delete(token);
    }



    /**
     * 往数据库添加一条用户信息
     *
     * 需要插入 user, user_role, user_info 三张表
     * 如果有某一个插入失败了整个事务需要回滚
     *
     * @param user
     * @param userInfo
     * @param roleId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user, UserInfo userInfo, int roleId){

        try {
            this.userMapper.addUser(user);
        }catch (Exception e){
            throw new ResultException(1001,"用户名已存在");
        }
        userInfo.setUserId(user.getId());
        userInfo.setRoleId(roleId);

        this.userInfoMapper.addUserInfo(userInfo);

        UserRole userRole = new UserRole();

        userRole.setUserId(user.getId());
        userRole.setRoleId(roleId);

        this.userRoleMapper.addUserRole(userRole);
    }



    /**
     * 将用户名, 密码 和其他固定值封装成 map
     * @param username
     * @param password
     * @return
     */
    private MultiValueMap<String, String> packagingLoginData(String username, String password){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);
        params.add("scope", "all");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        return params;
    }


    /**
     * 将用户数据转发到认证服务器获取 token
     * @param user
     * @return
     */
    private UserTokenDTO sendLoginData(User user){
        UserTokenDTO userTokenDTO = new UserTokenDTO();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> stringStringMultiValueMap = this.packagingLoginData(user.getUsername(), user.getPassword());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(stringStringMultiValueMap,headers);

        try {
            userTokenDTO = restTemplate.postForObject(oauthTokenUrl, requestEntity, UserTokenDTO.class);

            //将 token 作为 key, userId 作为 value 存到 redis
            user.setPassword(null);
            this.redisTemplate.opsForValue().set(userTokenDTO.getAccess_token(), user, 30669, TimeUnit.SECONDS);

            userTokenDTO.setCode(0);
            userTokenDTO.setMessage("succeed");
        }catch (Exception e){
            e.printStackTrace();
            userTokenDTO.setCode(1005);
            userTokenDTO.setMessage("用户名或密码错误");
            return userTokenDTO;
        }

        return userTokenDTO;
    }
}
