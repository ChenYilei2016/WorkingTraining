package cn.chenyilei.work.web.security.processor.wx;

import cn.chenyilei.work.domain.pojo.TbUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/17 14:27
 */
public interface WxUserDetailService {
    //根据openid 查找用户
    TbUser login(String openid);

    List<SimpleGrantedAuthority> getAuthority(String userId);
}
