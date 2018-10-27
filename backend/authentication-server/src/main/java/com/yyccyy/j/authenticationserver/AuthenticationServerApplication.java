package com.yyccyy.j.authenticationserver;

import com.yyccyy.j.authenticationserver.config.MyUserDetailsServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableCaching
@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("com.yyccyy.j.authenticationserver.mapper")
public class AuthenticationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServerApplication.class, args);
	}
	private static MyUserDetailsServiceImpl myUserDetailsService = new MyUserDetailsServiceImpl();

	public static MyUserDetailsServiceImpl getMyUserDetailsService() {
		return myUserDetailsService;
	}
}
