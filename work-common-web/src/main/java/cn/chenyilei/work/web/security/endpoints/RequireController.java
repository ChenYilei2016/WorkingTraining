package cn.chenyilei.work.web.security.endpoints;

import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.utils.CookieUtil;
import cn.chenyilei.work.utils.MvcUtils;
import cn.chenyilei.work.web.security.constant.WebSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/09 14:12
 */
@RequestMapping("/")
@Controller
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
public class RequireController {
    @Autowired
    WebSecurityProperties webSecurityProperties;

    @RequestMapping("/data")
    @ResponseBody
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object data(Authentication authentication,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        return "这里是测试返回的数据:"+authentication+"\n Url:"+request.getRequestURL()+"\n Uri:"+request.getRequestURI();
    }

    //没有登录的信息返回
    @RequestMapping("/authentication/require")
    public Object requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUrl = new HttpSessionRequestCache().getRequest(request,response).getRedirectUrl();
        //是Ajax请求
        if( CookieUtil.isAjax(request)  || !StringUtils.endsWith(redirectUrl,".html")){
            MvcUtils.setAjaxResponse(response);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AjaxResult.error("权限不足!"));
        }
        //页面请求
        response.sendRedirect(webSecurityProperties.getLoginPage());
        return null;
    }

}
