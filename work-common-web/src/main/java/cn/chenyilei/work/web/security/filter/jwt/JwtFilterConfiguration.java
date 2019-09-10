package cn.chenyilei.work.web.security.filter.jwt;

import cn.chenyilei.work.web.security.constant.WebSecurityProperties;
import cn.chenyilei.work.web.security.filter.FilterConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 18:21
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "cyl",name ="login-type",havingValue = "jwt",matchIfMissing = false)
public class JwtFilterConfiguration implements FilterConfiguration {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    WebSecurityProperties webSecurityProperties;

    @Override
    public void filterConfig(HttpSecurity http) throws Exception{
        log.info("启用JWT filter!");

        //JWT 不用保存session信息
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //进行登陆验证过滤器
        JwtCreateAuthenticationFilter jwtCreateAuthenticationFilter = new JwtCreateAuthenticationFilter(webSecurityProperties);
        jwtCreateAuthenticationFilter.setAuthenticationManager(authenticationManager);
        http.addFilterBefore(jwtCreateAuthenticationFilter, LogoutFilter.class);

        //获取用户信息过滤器
        JwtCheckAuthenticationFilter jwtCheckAuthenticationFilter = new JwtCheckAuthenticationFilter(webSecurityProperties);
        http.addFilterAfter(jwtCheckAuthenticationFilter,LogoutFilter.class);
    }
}
