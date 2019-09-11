package cn.chenyilei.work.commonutils;

import javax.servlet.http.HttpServletResponse;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/09 14:39
 */
public class MvcUtils {
    public final static void setAjaxResponse(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
    }
}
