package cn.chenyilei.work.commonutils;


import cn.chenyilei.work.domain.security.AuthenticationUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

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
@Component
public class JwtUtil implements InitializingBean, EnvironmentAware {

    /**
     * 默认加密密钥
     * application.yml  cyl.security.jwt.keySecret
     */
    private static byte[] KEYSECRET = "www.chenyilei.cn".getBytes();
    //凭证过期时长
    private static long ttl = 24*3600*1000 ;//以毫秒为单位

    private Environment environment;

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
        authenticationUser.setUserId(Objects.toString(body.get("userId")));
        authenticationUser.setUsername(Objects.toString(body.get("username")));

        //解析authorities
        if(body.containsKey("authorities")){
            List<LinkedHashMap<String, String>> authorities = (List<LinkedHashMap<String, String>>) body.get("authorities");
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            for (LinkedHashMap hashMap : authorities){
                String authority = hashMap.get("authority").toString();
                grantedAuthorityList.add(new SimpleGrantedAuthority(authority));
            }
            authenticationUser.setAuthorities(grantedAuthorityList);
        }

        return authenticationUser;
    }

    public static Map<String,Object> parseJWT(String jwt) {
        Claims body = Jwts.parser()
                .setSigningKey(KEYSECRET)
                .parseClaimsJws(jwt)
                .getBody();
        return body;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JwtUtil.KEYSECRET = this.environment.getProperty("cyl.security.jwt.keySecret","www.chenyilei.cn").getBytes();
        JwtUtil.ttl = this.environment.getProperty("cyl.security.jwt.ttl",long.class,24*3600*1000L);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}