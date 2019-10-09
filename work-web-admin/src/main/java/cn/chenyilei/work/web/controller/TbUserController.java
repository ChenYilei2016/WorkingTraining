package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.commonutils.JwtUtil;
import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.pojo.user.TbUser;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.domain.dto.wx.WxUserRequestBody;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.security.processor.wx.WxUserDetailService;
import cn.chenyilei.work.web.service.TbUserService;
import io.swagger.annotations.Api;
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
@Api(tags = "用户相关接口 TbUserController")
@Controller
@ResponseBody
@RequestMapping("/user")
public class TbUserController{
    @Autowired
    TbUserService tbUserService;
    @Autowired
    WxUserDetailService wxUserDetailService;

    @ApiOperation("得到用户的相关信息!")
    @GetMapping("/detail")
    public AjaxResult<TbUser> userDetail(){
        TbUser tbUser = tbUserService.selectUserDetail();
        return AjaxResult.success(tbUser,"查询成功!");
    }

    @ApiOperation("修改自己的相关信息!")
    @PutMapping("/update")
    public AjaxResult update( @ApiParam("传入需要更新的参数!") @RequestBody WxUserRequestBody wxUserRequestBody ){
        AuthenticationUser securityUser = SecurityContext.getSecurityContextPrincipal(AuthenticationUser.class);
        tbUserService.update(securityUser.getUserId(),wxUserRequestBody);
        return AjaxResult.success(null,"修改成功!");
    }

    /**
     * 微信绑定用户 和 身份的关系
     * body : level用户的绑定等级
     * @return
     */
    @ApiOperation("首次登陆小程序绑定自己的账号身份!")
    @PostMapping("/binding")
    public AjaxResult<String> binding ( @ApiParam("仅需要level参数") @RequestBody WxUserRequestBody wxUserRequestBody){
        if(null == wxUserRequestBody.getLevel()){
            return AjaxResult.error("没有level参数!", CodeResultEnum.BAD_REQUEST);
        }
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal(AuthenticationUser.class);
        tbUserService.bindingUser(user.getUserId(),wxUserRequestBody.getLevel());
        user.setAuthorities(wxUserDetailService.getAuthority(user.getUserId()));
        return AjaxResult.success(JwtUtil.createJWT(user),"绑定成功!"); //刷新token
    }


}
