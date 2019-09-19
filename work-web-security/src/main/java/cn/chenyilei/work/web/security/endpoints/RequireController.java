package cn.chenyilei.work.web.security.endpoints;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.commonutils.CookieUtil;
import cn.chenyilei.work.commonutils.MvcUtils;
import cn.chenyilei.work.web.security.properties.WebSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
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
@Controller
@RequestMapping("/")
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
public class RequireController {
    @Autowired
    WebSecurityProperties webSecurityProperties;

    @RequestMapping("/data")
    @ResponseBody
    public Object data3(@AuthenticationPrincipal AuthenticationUser user,
                        Authentication authentication,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        String data = "authentication:"+authentication+"\n " +
                "user: "+user+ "\n"+
                "Url:"+request.getRequestURL()+"\n " +
                "Uri:"+request.getRequestURI()+"\n";
        return AjaxResult.success(data,"一次成功的请求!");
    }

    @RequestMapping("/data2")
    @ResponseBody
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Object data(@AuthenticationPrincipal AuthenticationUser user,
                        Authentication authentication,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String data = "authentication:"+authentication+"\n " +
                "user: "+user+ "\n"+
                "Url:"+request.getRequestURL()+"\n " +
                "Uri:"+request.getRequestURI()+"\n";
        return AjaxResult.success(data,"一次成功的请求!");
    }

    @RequestMapping("/data3")
    @ResponseBody
    @PreAuthorize("hasAuthority('ALL')")
    public Object data2(@AuthenticationPrincipal AuthenticationUser user,
                       Authentication authentication,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String data = "authentication:"+authentication+"\n " +
                "user: "+user+ "\n"+
                "Url:"+request.getRequestURL()+"\n " +
                "Uri:"+request.getRequestURI()+"\n";
        return AjaxResult.success(data,"一次成功的请求!");
    }




    //没有登录的信息返回
    @RequestMapping("/authentication/require")
    public Object requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        String redirectUrl = "";
        if(savedRequest != null){
            redirectUrl = savedRequest.getRedirectUrl();
        }
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
