package cn.chenyilei.work.web.security.filter.jwt;

import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.utils.JwtUtil;
import cn.chenyilei.work.web.security.constant.WebSecurityProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 20:05
 */
@Slf4j
public class JwtCheckAuthenticationFilter extends OncePerRequestFilter {
    private WebSecurityProperties webSecurityProperties ;

    public JwtCheckAuthenticationFilter(WebSecurityProperties webSecurityProperties) {
        this.webSecurityProperties = webSecurityProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String token = request.getHeader(webSecurityProperties.getTokenHeader());

        //没有 token 的话就算了,直接放行
        if (StringUtils.isEmpty(token)) {
            chain.doFilter(request, response);
            return;
        }

        if(SecurityContextHolder.getContext().getAuthentication() != null){
            chain.doFilter(request, response);
            return;
        }

        AuthenticationUser authenticationUser = null;
        try {
            authenticationUser = JwtUtil.parseJWTAuthenticationUser(token);
        } catch (IllegalArgumentException e) {
            logger.error("An error occured during getting username from token", e);
        } catch (ExpiredJwtException e) {
            logger.warn("The token is expired and not valid anymore", e);
        } catch (MalformedJwtException e) {
            logger.info(" Unable to read JSON", e);
        }

        if(authenticationUser == null){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authenticationUser,null,authenticationUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        log.info("authenticated user {}, setting security context",  authenticationUser.getUsername() );

        chain.doFilter(request,response);
    }
}
