package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.pojo.TbUser;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:38
 */
public interface TbUserService extends CommonService<TbUser> {
    void bindingUser(String userId, Integer level);
    void updatename(String userId, String username);
}
