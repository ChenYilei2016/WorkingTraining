package cn.chenyilei.work.utils;

import cn.chenyilei.work.domain.security.AuthenticationUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sound.sampled.Line;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 15:54
 */
@Slf4j
public class JwtUtilTest {

    @Test
    public void createJWT() throws UnsupportedEncodingException {

        AuthenticationUser authenticationUser = new AuthenticationUser();
        authenticationUser.setUserId(123123L);
        authenticationUser.setUsername("rooot");
        authenticationUser.setAuthorities(AuthorityUtils.createAuthorityList("123"));
        String jwt = JwtUtil.createJWT(authenticationUser);
        log.info(jwt);

        AuthenticationUser authenticationUser1 = JwtUtil.parseJWTAuthenticationUser(jwt);
        Collection<? extends GrantedAuthority> authorities = authenticationUser1.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        while (iterator.hasNext()){

            System.out.println(iterator.next().getAuthority());
        }

    }

    @Test
    public void parseJWTAuthenticationUser() {

    }
}