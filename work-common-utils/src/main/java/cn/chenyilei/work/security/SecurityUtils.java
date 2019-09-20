package cn.chenyilei.work.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/19 13:52
 */
public class SecurityUtils {

    public static <T extends UserDetails> T getSecurityContextPrincipal(Class<T> clazz){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if( null == authentication){
            throw new AuthenticationCredentialsNotFoundException("无用户认证信息!");
        }
        return (T) authentication.getPrincipal();
    }
}
