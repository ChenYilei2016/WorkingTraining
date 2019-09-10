package cn.chenyilei.work.web.security.service;

import cn.chenyilei.work.domain.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 13:08
 */
public class DaoUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
