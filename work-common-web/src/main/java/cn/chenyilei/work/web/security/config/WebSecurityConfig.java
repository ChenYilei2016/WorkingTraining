package cn.chenyilei.work.web.security.config;


import cn.chenyilei.work.web.constant.WebSecurityProperties;
import cn.chenyilei.work.web.security.exception.AuthenticationAccessDeniedHandler;
import cn.chenyilei.work.web.security.exception.AuthenticationFailEntryPoint;
import cn.chenyilei.work.web.security.filter.FilterConfiguration;
import cn.chenyilei.work.web.security.service.TestUserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 16:16
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(WebSecurityProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    WebSecurityProperties webSecurityProperties;

    @Autowired(required = false)
    List<FilterConfiguration> filterConfigurationList = new ArrayList<>();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();

        http.formLogin().disable();
//                .successHandler(successHandler)
//                .failureHandler(failureHandler)
//                .loginPage("/authentication/require")
//                .loginProcessingUrl("/authentication/form")

        //特殊允许通过路径
        http.authorizeRequests()
                .antMatchers(
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
                        "/code/**",
                        "/authentication/require",
                        webSecurityProperties.getLoginPath(),webSecurityProperties.getLoginPage(),

                        //测试通过
                        "/data"
                ).permitAll()
        ;

        //拦截其余Url
        http.authorizeRequests().anyRequest().authenticated();

//      AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
//      AccessDeineHandler       用来解决认证过的用户访问无权限资源时的异常
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationFailEntryPoint())
                .accessDeniedHandler(new AuthenticationAccessDeniedHandler())
        ;

        //自定义过滤器设置
        diyFilterConfiguration(http);
    }

    private void diyFilterConfiguration(HttpSecurity http) throws Exception {
        for(FilterConfiguration filterConfiguration : filterConfigurationList){
            filterConfiguration.filterConfig(http);
        }
        if(filterConfigurationList.isEmpty() && webSecurityProperties.isPermitAllWhenNoFilter() ){
            log.warn("没有加入任何拦截器,默认打开所有访问权限!");
            http.authorizeRequests().anyRequest().permitAll();
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder())
        ;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        return new TestUserDetailServiceImpl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
