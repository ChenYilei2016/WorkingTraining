package cn.chenyilei.work.web.security.service;

import cn.chenyilei.work.domain.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 13:09
 */

@Service
public class TestUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        //Test 测试Service
        return new AuthenticationUser("-1",username,passwordEncoder.encode("root"), AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
