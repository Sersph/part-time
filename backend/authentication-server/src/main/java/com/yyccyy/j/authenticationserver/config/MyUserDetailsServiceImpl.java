package com.yyccyy.j.authenticationserver.config;

import com.yyccyy.j.authenticationserver.mapper.RoleMapper;
import com.yyccyy.j.authenticationserver.mapper.UserMapper;
import com.yyccyy.j.authenticationserver.mapper.UserRoleMapper;
import com.yyccyy.j.authenticationserver.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author innoyiya
 * @date 2018/9/22.
 */
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.yyccyy.j.authenticationserver.pojo.User user = this.userMapper.selectUserByUsername(username);

        Role role = this.roleMapper.selectRoleById(this.userRoleMapper.selectUserRoleIdByUserId(user.getId()));


        UserDetails userDetailsBySql = User.withUsername(username)
                                            .password(user.getPassword())
                                            .authorities(role.getName())
                                            .build();

        return new User(
                userDetailsBySql.getUsername(),
                userDetailsBySql.getPassword(),
                userDetailsBySql.isEnabled(),
                userDetailsBySql.isAccountNonExpired(),
                userDetailsBySql.isCredentialsNonExpired(),
                userDetailsBySql.isAccountNonLocked(),
                userDetailsBySql.getAuthorities()
        );
    }
}
