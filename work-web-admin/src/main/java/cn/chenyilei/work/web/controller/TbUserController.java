package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.commonutils.JwtUtil;
import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.domain.dto.wx.WxUserRequestBody;
import cn.chenyilei.work.security.SecurityUtils;
import cn.chenyilei.work.web.security.processor.wx.WxUserDetailService;
import cn.chenyilei.work.web.service.TbUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 对于用户操作的接口
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:31
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class TbUserController{
    @Autowired
    TbUserService tbUserService;
    @Autowired
    WxUserDetailService wxUserDetailService;

    /**
     * 修改自己的用户名
     */
    @ApiOperation("修改自己的用户名!")
    @PutMapping("/updatename")
    public AjaxResult updatename( @ApiParam("仅需要username参数") @RequestBody WxUserRequestBody wxUserRequestBody ){
        if(null == wxUserRequestBody.getUsername()){
            return AjaxResult.error("没有username参数!", CodeResultEnum.BAD_REQUEST);
        }
        AuthenticationUser securityUser = SecurityUtils.getSecurityContextPrincipal(AuthenticationUser.class);
        tbUserService.updatename(securityUser.getUserId(),wxUserRequestBody.getUsername());
        return AjaxResult.success(null,"修改成功!");
    }

    /**
     * 微信绑定用户 和 身份的关系
     * body : level用户的绑定等级
     * @return
     */
    @ApiOperation("首次登陆小程序绑定自己的账号身份!")
    @PostMapping("/binding")
    public AjaxResult binding ( @ApiParam("仅需要level参数") @RequestBody WxUserRequestBody wxUserRequestBody){
        if(null == wxUserRequestBody.getLevel()){
            return AjaxResult.error("没有level参数!", CodeResultEnum.BAD_REQUEST);
        }
        AuthenticationUser user = SecurityUtils.getSecurityContextPrincipal(AuthenticationUser.class);
        Integer level = wxUserRequestBody.getLevel();
        tbUserService.bindingUser(user.getUserId(),level);
        user.setAuthorities(wxUserDetailService.getAuthority(user.getUserId()));
        return AjaxResult.success(JwtUtil.createJWT(user),"绑定成功!"); //刷新token
    }

}
