package cn.chenyilei.work.web.security.exception;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.commonutils.MapperUtils;
import cn.chenyilei.work.commonutils.MvcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 有人无凭据就想访问受保护的资源时，用这个处理
 * 返回默认的 json 字符串
 *
 * @author pyb
 * @date 2019/08/31
 */
@Slf4j
public class AuthenticationFailEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        log.error("AuthenticationFailEntryPoint");

        CodeResultEnum unauthorized = CodeResultEnum.UNAUTHORIZED;
        MvcUtils.setAjaxResponse(response);
        AjaxResult error = AjaxResult.error(e.getMessage(), unauthorized);
        response.setStatus(unauthorized.getCode());
        response.getWriter().print(MapperUtils.obj2json(error));
        response.flushBuffer();

    }
}
