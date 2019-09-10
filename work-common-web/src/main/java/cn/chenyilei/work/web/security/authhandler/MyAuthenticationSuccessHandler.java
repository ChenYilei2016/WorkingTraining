package cn.chenyilei.work.web.security.authhandler;

import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.utils.CookieUtil;
import cn.chenyilei.work.utils.MvcUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
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
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("登陆成功!");

        if( CookieUtil.isAjax(httpServletRequest) ){
            MvcUtils.setAjaxResponse(httpServletResponse);
            httpServletResponse.setStatus(200);
            AjaxResult success = AjaxResult.success(authentication, "登陆成功!");
            httpServletResponse.getWriter().print(objectMapper.writeValueAsString(success));
        }else{
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
