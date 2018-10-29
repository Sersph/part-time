package com.tidc.parttimemonarch.security;

import com.tidc.parttimemonarch.security.message.AuthExceptionEntryPoint;
import com.tidc.parttimemonarch.security.message.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @author innoyiya
 * @date 2018/9/25.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 认证服务器认证接口
     */
    @Value("${ResourceServerConfiguration.checkTokenEndpointUrl}")
    private String checkTokenEndpointUrl;

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

    @Autowired
    public ResourceServerConfiguration(CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(clientSecret);
        return tokenService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().cors()
                .and().csrf().disable()
                .authorizeRequests().anyRequest().permitAll();
    }




    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 验证token失败
     *
     * @param resources resources
     * @throws Exception resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler);
    }
}