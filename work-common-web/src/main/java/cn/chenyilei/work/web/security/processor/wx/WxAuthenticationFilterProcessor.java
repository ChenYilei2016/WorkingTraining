package cn.chenyilei.work.web.security.processor.wx;

import cn.chenyilei.work.commonutils.MapperUtils;
import cn.chenyilei.work.domain.pojo.TbUser;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.web.security.processor.AuthenticationFilterProcessor;
import cn.chenyilei.work.wx.WxSmallProgramUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 对于微信小程序的一种登陆业务验证
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 12:23
 */
@Component
public class WxAuthenticationFilterProcessor implements AuthenticationFilterProcessor {

    private WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();

    @Autowired
    WxUserdetailServiceImpl wxUserdetailService;

    @Override
    public Authentication doAttemptAuthentication(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  AuthenticationManager authenticationManager) throws IOException, AuthenticationException {
        try {
            //相当于requestBody 转实体类
            String requestBody = IOUtils.toString(request.getInputStream());
            Map<String, Object> requestBodyMap = MapperUtils.json2map(requestBody);
            if(!requestBodyMap.containsKey("code")){
                throw new UnapprovedClientAuthenticationException("没有code参数");
            }
            String code = requestBodyMap.get("code").toString();
            String openid = WxSmallProgramUtils.getOpenIdFromCode(code);

            //TODO: 使更加优雅
            //进行账号的登陆或者注册
            TbUser tbUser = wxUserdetailService.login(openid);
            AuthenticationUser user = new AuthenticationUser(
                     tbUser.getId()
                    ,openid
                    ,null
                    ,AuthorityUtils.commaSeparatedStringToAuthorityList(tbUser.getAuthorities()));

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,null,null);
            token.setDetails(webAuthenticationDetailsSource.buildDetails(request));

            return token;
        }catch(NullPointerException e){
            throw new InternalAuthenticationServiceException("发生了不可预料的错误,请联系管理员QQ705029004 !");
        } catch (Exception e){
            throw new UnapprovedClientAuthenticationException(e.getMessage());
        }
    }
}
