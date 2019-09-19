package cn.chenyilei.work.web.security.rbac;

import cn.chenyilei.work.web.security.filter.FilterConfiguration;
import cn.hutool.core.util.RandomUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/18 18:37
 */
//@Configuration
public class RBACFilterConfiguration implements FilterConfiguration {
    @Override
    public void filterConfig(HttpSecurity http) throws Exception {
        http.addFilterBefore(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request
                    , HttpServletResponse response
                    , FilterChain filterChain) throws ServletException, IOException {
                if(RandomUtil.randomInt(100) < 50)
                    throw new AccessDeniedException("用户所具有的权限不足!");
                filterChain.doFilter(request,response);
            }
        }, FilterSecurityInterceptor.class);
    }
}
