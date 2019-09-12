package cn.chenyilei.work.web.security.processor.browser;

import cn.chenyilei.work.web.security.processor.AuthenticationFilterProcessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对于微信的一种登陆业务验证
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 12:23
 */
@Component
public class FormAuthenticationFilterProcessor implements AuthenticationFilterProcessor {

    private WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();

    @Override
    public Authentication doAttemptAuthentication(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  AuthenticationManager authenticationManager) throws IOException, AuthenticationException {
        try {
            //相当于requestBody 转实体类
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);
            authRequest.setDetails(webAuthenticationDetailsSource.buildDetails(request));

            return authenticationManager.authenticate(authRequest);
        }catch (Exception e){
            throw new UnapprovedClientAuthenticationException(e.getMessage());
        }
    }
}
