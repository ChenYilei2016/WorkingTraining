package cn.chenyilei.work.web.security.config;


import cn.chenyilei.work.web.security.properties.WebSecurityProperties;
import cn.chenyilei.work.web.security.exception.AuthenticationAccessDeniedHandler;
import cn.chenyilei.work.web.security.exception.AuthenticationFailEntryPoint;
import cn.chenyilei.work.web.security.filter.FilterConfiguration;
import cn.chenyilei.work.web.security.service.TestUserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;
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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements EnvironmentAware {

    @Autowired
    WebSecurityProperties webSecurityProperties;

    @Autowired(required = false)
    List<FilterConfiguration> filterConfigurationList = new ArrayList<>();

    private Environment environment ;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        final Header[] headers = new Header[]{
                //支持所有源的访问
                new Header("Access-control-Allow-Origin", "*"),
                //使ajax请求能够取到header中的jwt token信息
                new Header("Access-Control-Expose-Headers", webSecurityProperties.getTokenHeader())
        };

        //跨域配置，支持跨域
        http.cors()
                .and()   //添加header设置，支持跨域和ajax请求
                .headers()
                .addHeaderWriter(new StaticHeadersWriter(Arrays.asList(headers)))
                .and()
                //拦截OPTIONS请求，直接返回header
                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class);

        http.formLogin().disable();
//                .successHandler(successHandler)
//                .failureHandler(failureHandler)
//                .loginPage("/authentication/require")
//                .loginProcessingUrl("/authentication/form")

        //特殊允许通过路径
        http.authorizeRequests()
                .antMatchers(
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
                        "/code/**","/**/*.ico",
                        "/authentication/require",
                        webSecurityProperties.getLoginPath(),webSecurityProperties.getLoginPage(),

                        //测试通过
                        "/data"
                ).permitAll()
        ;

//      AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
//      AccessDeineHandler       用来解决认证过的用户访问无权限资源时的异常
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationFailEntryPoint())
                .accessDeniedHandler(new AuthenticationAccessDeniedHandler())
        ;


//        进行url的RBAC权限控制
//        http.authorizeRequests()
//                .anyRequest()
//                .access("@rbacService.hasPermission(request,authentication)");

        //自定义过滤器设置
        diyFilterConfiguration(http);


        if(false == webSecurityProperties.isEnabledSecurity()){
            http.authorizeRequests().anyRequest().permitAll();
        }else{
            //拦截其余Url
            http.authorizeRequests().anyRequest().authenticated();
        }
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

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
