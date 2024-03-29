package cn.chenyilei.work.web.security.filter.jwt;

import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.commonutils.JwtUtil;
import cn.chenyilei.work.commonutils.MapperUtils;

import cn.chenyilei.work.commonutils.MvcUtils;
import cn.chenyilei.work.web.security.properties.WebSecurityProperties;
import cn.chenyilei.work.web.security.processor.AuthenticationFilterProcessorContextHolder;
import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.util.SetUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对于使用jwt方式,验证方式可能区别很大所以弄成了多个过滤器
 *
 * 可以匹配 /login/** 路径 根据末尾的 sms/imageCode 实现多个业务处理器
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 15:44
 */
public class JwtCreateAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private WebSecurityProperties webSecurityProperties ;
    private AuthenticationFilterProcessorContextHolder authenticationFilterProcessorContextHolder;


    private JwtCreateAuthenticationFilter(WebSecurityProperties webSecurityProperties) {
        super(new AntPathRequestMatcher(webSecurityProperties.getLoginPath(), "POST"));
        this.webSecurityProperties = webSecurityProperties;
    }

    public JwtCreateAuthenticationFilter(WebSecurityProperties webSecurityProperties, AuthenticationFilterProcessorContextHolder authenticationFilterProcessorContextHolder) {
        this(webSecurityProperties);
        this.authenticationFilterProcessorContextHolder = authenticationFilterProcessorContextHolder;
    }

    public void nowInit() {
        this.setAuthenticationSuccessHandler(new SuccessHandler());
        this.setAuthenticationFailureHandler(new FailureHandler());
        //不将认证后的context放入session
        this.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        if (username == null) {
//            username = "";
//        }
//        if (password == null) {
//            password = "";
//        }
//        username = username.trim();
//        /**
//         * 可以直接生成一个token 用其他的验证方式 {@link AuthenticationUser}
//         */
////        AuthenticationUser authenticationUser = new AuthenticationUser();
////        authenticationUser.setUserId(122L);
////        authenticationUser.setUsername("12312312");
////        authenticationUser.setAuthorities(AuthorityUtils.createAuthorityList("123"));
////        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(authenticationUser,null,AuthorityUtils.createAuthorityList("123"));
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);
//        setDetails(request, authRequest);
//        return getAuthenticationManager().authenticate(authRequest);
        return authenticationFilterProcessorContextHolder
                .findFilterProcessorByRequest(request)
                .doAttemptAuthentication(request,response,getAuthenticationManager());
    }

    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class JWTData implements Serializable {
        private String token;
        private List<String> authorities;
    }

    /**
     /**
     * 用户名和密码效验正确的处理器
     * 生成一个token
     */
    public class SuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            /**
             * 返回jwt
             */
            AuthenticationUser user = (AuthenticationUser) authentication.getPrincipal();
            String token = JwtUtil.createJWT(user);
            JWTData jwtData = new JWTData(token,
                    user.getAuthorities().stream()
                    .map( x->((SimpleGrantedAuthority)x).getAuthority() )
                    .collect(Collectors.toList())
            );
            MvcUtils.setAjaxResponse(response);
            response.setStatus(200);
            AjaxResult success = AjaxResult.success(jwtData, "登陆成功!");
            response.getWriter().print(MapperUtils.obj2json(success));
        }
    }
    /**
     * 登陆失败了那就将这异常往外边传
     */
    public class FailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException, AuthenticationException {
            MvcUtils.setAjaxResponse(response);
            response.setStatus(401);
            AjaxResult error = AjaxResult.error(exception.getMessage());
            response.getWriter().print(MapperUtils.obj2json(error));
        }
    }
}
