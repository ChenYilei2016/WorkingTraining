package cn.chenyilei.work.utils;

import cn.chenyilei.work.domain.security.AuthenticationUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 <dependency>
 <groupId>io.jsonwebtoken</groupId>
 <artifactId>jjwt</artifactId>
 <version>0.9.0</version>
 </dependency>
 <dependency>
 <groupId>joda-time</groupId>
 <artifactId>joda-time</artifactId>
 </dependency>
 * Created by Administrator on 2018/4/11.
 */
public class JwtUtil {

    //加密密钥
    private static byte[] KEYSECRET = "www.chenyilei.cn".getBytes();
    //凭证过期时长
    private static long ttl = 24*3600*1000 ;//以毫秒为单位

    /**
     * 生成JWT
     * @return
     */
    public static String createJWT(AuthenticationUser authenticationUser) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, KEYSECRET)
                .claim("authorities", authenticationUser.getAuthorities())
                .claim("username",authenticationUser.getUsername())
                .claim("userId",authenticationUser.getUserId())
                ;
        if (ttl > 0) {
            builder.setExpiration( new Date( nowMillis + ttl));
        }
        return builder.compact();
    }
    public static AuthenticationUser parseJWTAuthenticationUser(String jwt) {
        Claims body = Jwts.parser()
                .setSigningKey(KEYSECRET)
                .parseClaimsJws(jwt)
                .getBody();
        AuthenticationUser authenticationUser = new AuthenticationUser();
        authenticationUser.setUserId(Long.valueOf(body.get("userId").toString()));
        authenticationUser.setUsername(body.get("username").toString());
        //解析authorities
        List<LinkedHashMap<String, String>> authorities = (List<LinkedHashMap<String, String>>) body.get("authorities");
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (LinkedHashMap hashMap : authorities){
            String authority = hashMap.get("authority").toString();
            grantedAuthorityList.add(new SimpleGrantedAuthority(authority));
        }

        authenticationUser.setAuthorities(grantedAuthorityList);
        return authenticationUser;
    }

    public static Map<String,Object> parseJWT(String jwt) {
        Claims body = Jwts.parser()
                .setSigningKey(KEYSECRET)
                .parseClaimsJws(jwt)
                .getBody();
        return body;
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
//    public static JwtInterceptor.UserInfo parseJWT(String jwtStr){
//        io.jsonwebtoken.Claims body = Jwts.parser()
//                .setSigningKey(KEYSECRET)
//                .parseClaimsJws(jwtStr)
//                .getBody();
//        JwtInterceptor.UserInfo userInfo = new JwtInterceptor.UserInfo();
//        userInfo.setId(body.getId());
//        userInfo.setUsername(body.getSubject());
//        userInfo.setAuthorities( (List<String>)body.get("authorities") );
//        return userInfo;
//    }
}