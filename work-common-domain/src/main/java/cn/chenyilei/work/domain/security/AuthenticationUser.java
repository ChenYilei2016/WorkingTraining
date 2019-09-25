package cn.chenyilei.work.domain.security;

import cn.chenyilei.work.domain.pojo.user.TbUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Spring Security框架服务的用户类
 *
 * @author pyb
 * @date 2019/28/31
 */
@Data
@ToString
@NoArgsConstructor
public class AuthenticationUser implements UserDetails {

    private String userId;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthenticationUser(String userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 以下用户认证默认返回true，具体权限自己设计，不交给SpringSecurity
     * -------------------------------------------------------
     */

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public static AuthenticationUser fromTbUser(TbUser tbUser){
        AuthenticationUser authenticationUser = new AuthenticationUser();
        authenticationUser.setUserId(tbUser.getUserId());
        authenticationUser.setPassword(tbUser.getPassword());
        authenticationUser.setUsername(tbUser.getUsername());
        return authenticationUser;
    }

}
