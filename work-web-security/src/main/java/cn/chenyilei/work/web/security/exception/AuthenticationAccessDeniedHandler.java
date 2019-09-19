package cn.chenyilei.work.web.security.exception;

import cn.chenyilei.work.commonutils.MapperUtils;
import cn.chenyilei.work.commonutils.MvcUtils;
import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 14:43
 */
@Slf4j
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("AuthenticationAccessDeniedHandler");

        CodeResultEnum forbidden = CodeResultEnum.FORBIDDEN;
        MvcUtils.setAjaxResponse(response);
        AjaxResult error = AjaxResult.error(accessDeniedException.getMessage(),forbidden);
        response.setStatus(forbidden.getCode());
        response.getWriter().print(MapperUtils.obj2json(error));
        response.flushBuffer();
    }
}
