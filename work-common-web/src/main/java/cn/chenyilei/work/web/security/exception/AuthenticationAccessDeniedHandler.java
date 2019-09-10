package cn.chenyilei.work.web.security.exception;

import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.utils.MapperUtils;
import cn.chenyilei.work.utils.MvcUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 14:43
 */
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        MvcUtils.setAjaxResponse(response);
        AjaxResult error = AjaxResult.error(accessDeniedException.getMessage());
        response.getWriter().print(MapperUtils.obj2json(error));
        response.flushBuffer();
    }
}
