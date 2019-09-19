package cn.chenyilei.work.web.security.processor.wx;

import cn.chenyilei.work.domain.pojo.TbPermission;
import cn.chenyilei.work.domain.pojo.TbUser;
import cn.chenyilei.work.web.mapper.TbUserMapperSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 微信专用UserdetailService
 * @see {@link cn.chenyilei.work.web.security.processor.wx.WxAuthenticationFilterProcessor}
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 16:03
 */
@Component
public class WxUserDetailServiceImpl implements WxUserDetailService{

    @Autowired
    TbUserMapperSecurity tbUserMapperSecurity;

    //根据openid 查找用户
    @Override
    public TbUser login(String openid) {
        TbUser tbUser = new TbUser();
        tbUser.setWxopenid(openid);
        TbUser selectUser = tbUserMapperSecurity.selectOne(tbUser);

        if(selectUser != null){
            return selectUser;
        }
        //注册
        tbUser.setUsername(openid);
        //自动带上附赠ID
        int insert = tbUserMapperSecurity.insert(tbUser);
        return tbUser;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthority(String userId) {
        List<TbPermission> permissionList = tbUserMapperSecurity.findPermissionsByUserId(userId);
        return permissionList.stream().map(x -> {
            return new SimpleGrantedAuthority(x.getPermissionName());
        }).collect(Collectors.toList());
    }
}
