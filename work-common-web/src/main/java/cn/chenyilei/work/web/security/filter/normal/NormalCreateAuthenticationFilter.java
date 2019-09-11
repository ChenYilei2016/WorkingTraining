package cn.chenyilei.work.web.security.filter.normal;

import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.utils.MapperUtils;
import cn.chenyilei.work.utils.MvcUtils;
import cn.chenyilei.work.web.security.constant.WebSecurityProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 13:25
 */
public class NormalCreateAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private WebSecurityProperties webSecurityProperties;

    public NormalCreateAuthenticationFilter(WebSecurityProperties webSecurityProperties) {
        //拦截url默认为 /authentication/login 的POST请求
        super(new AntPathRequestMatcher(webSecurityProperties.getLoginPath(), "POST"));
        this.webSecurityProperties = webSecurityProperties;

        nowInit();
    }

    private void nowInit() {
        this.setAuthenticationSuccessHandler(new SuccessHandler());
        this.setAuthenticationFailureHandler(new FailureHandler());
        //不将认证后的context放入session
        this.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        username = username.trim();
        /**
         * 可以直接生成一个token 用其他的验证方式
         */
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);
        setDetails(request, authRequest);

        return getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
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
             * 可自定义登陆成功返回信息
             */
            AuthenticationUser user = (AuthenticationUser) authentication.getPrincipal();

            MvcUtils.setAjaxResponse(response);
            response.setStatus(200);
            AjaxResult success = AjaxResult.success(authentication, "登陆成功!");
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
            AjaxResult error = AjaxResult.error("登陆信息有误!");
            response.getWriter().print(MapperUtils.obj2json(error));
        }
    }
}
