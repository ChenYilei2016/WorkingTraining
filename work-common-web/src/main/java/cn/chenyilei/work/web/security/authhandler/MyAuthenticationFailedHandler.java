package cn.chenyilei.work.web.security.authhandler;

import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.utils.CookieUtil;
import cn.chenyilei.work.utils.MapperUtils;
import cn.chenyilei.work.utils.MvcUtils;
import cn.chenyilei.work.web.security.constant.WebSecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/30- 15:17
 */
@Component
@Slf4j
public class MyAuthenticationFailedHandler implements AuthenticationFailureHandler {
    @Autowired
    ObjectMapper objectMapper;

    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    WebSecurityProperties webSecurityProperties;


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {

        if(!CookieUtil.isAjax(httpServletRequest)){
            MvcUtils.setAjaxResponse(httpServletResponse);
            httpServletResponse.setStatus(500);
            AjaxResult error = AjaxResult.error(e.getMessage());
            httpServletResponse.getWriter().print(objectMapper.writeValueAsString(error));
        }else{
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,webSecurityProperties.getLoginPage()+"?msg="+e.getMessage());
        }
    }
}
