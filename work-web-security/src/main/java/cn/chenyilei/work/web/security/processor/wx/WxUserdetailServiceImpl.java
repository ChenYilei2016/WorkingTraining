package cn.chenyilei.work.web.security.processor.wx;

import cn.chenyilei.work.domain.pojo.TbUser;
import cn.chenyilei.work.domain.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 微信专用UserdetailService
 * @see {@link cn.chenyilei.work.web.security.processor.wx.WxAuthenticationFilterProcessor}
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 16:03
 */
@Component
public class WxUserdetailServiceImpl {
    @Autowired
    TbUserMapper tbUserMapper;

    //根据openid 查找用户
    public TbUser login(String openid) {
        TbUser tbUser = new TbUser();
        tbUser.setWxopenid(openid);
        TbUser selectUser = tbUserMapper.selectOne(tbUser);
        if(selectUser == null){
            //注册
            tbUser.setUsername(openid);
            //自动带上附赠ID
            int insert = tbUserMapper.insert(tbUser);
            return tbUser;
        }
        return selectUser;
    }
}
