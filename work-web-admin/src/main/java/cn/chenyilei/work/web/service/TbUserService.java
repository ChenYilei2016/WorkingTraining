package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.dto.wx.WxUserRequestBody;
import cn.chenyilei.work.domain.pojo.user.TbUser;
import cn.chenyilei.work.domain.pojo.internal_enum.UserLevelEnum;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:38
 */
public interface TbUserService extends CommonService<TbUser>{
    void bindingUser(String userId, UserLevelEnum level);
    void updatename(String userId, String username);

    void update(String userId, WxUserRequestBody wxUserRequestBody);

    TbUser selectUserDetail();

    TbUser selectUserDetailById(String userId);
}
