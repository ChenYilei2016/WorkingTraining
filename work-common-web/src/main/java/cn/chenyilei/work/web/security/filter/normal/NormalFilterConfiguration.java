package cn.chenyilei.work.web.security.filter.normal;

import cn.chenyilei.work.web.constant.WebSecurityProperties;
import cn.chenyilei.work.web.security.filter.FilterConfiguration;
import cn.chenyilei.work.web.security.processor.AuthenticationFilterProcessorContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 14:02
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "cyl",name ="authentication-type",havingValue = "normal",matchIfMissing = false)
public class NormalFilterConfiguration implements FilterConfiguration {

    @Autowired
    WebSecurityProperties webSecurityProperties;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationFilterProcessorContextHolder authenticationFilterProcessorContextHolder;

    @Override
    public void filterConfig(HttpSecurity httpSecurity) throws Exception{
        log.info("启用normal filter!");
        NormalCreateAuthenticationFilter normalAuthenticationFilter =
                new NormalCreateAuthenticationFilter(webSecurityProperties,authenticationFilterProcessorContextHolder);
        normalAuthenticationFilter.setAuthenticationManager(authenticationManager);
        normalAuthenticationFilter.nowInit();

        //将认证的 filter 放到 logoutFilter 这个责任链节点之前
        httpSecurity
                .addFilterBefore(normalAuthenticationFilter, LogoutFilter.class);
    }
}
