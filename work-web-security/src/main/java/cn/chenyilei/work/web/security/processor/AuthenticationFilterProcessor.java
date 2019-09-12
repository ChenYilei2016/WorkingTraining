package cn.chenyilei.work.web.security.processor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 真正为自定义过滤器作业务过滤的执行器
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 11:06
 */
public interface AuthenticationFilterProcessor {
    public Authentication doAttemptAuthentication(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  AuthenticationManager authenticationManager) throws AuthenticationException,IOException;
}
