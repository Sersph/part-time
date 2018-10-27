package com.yyccyy.j.authenticationserver;

import com.yyccyy.j.authenticationserver.config.MyUserDetailsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServerApplication.class, args);
	}
	private static MyUserDetailsServiceImpl myUserDetailsService = new MyUserDetailsServiceImpl();

	public static MyUserDetailsServiceImpl getMyUserDetailsService() {
		return myUserDetailsService;
	}
}
