package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.mapper.TbUserRoleMapper;
import cn.chenyilei.work.domain.pojo.TbUser;

import cn.chenyilei.work.domain.pojo.TbUserRole;
import cn.chenyilei.work.web.mapper.TbUserMapperExt;
import cn.chenyilei.work.web.mapper.TbUserMapperSecurity;
import cn.chenyilei.work.web.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:40
 */
@Service
public class TbUserServiceImpl extends CommonServiceImpl<TbUser,TbUserMapperExt> implements TbUserService {

    @Autowired
    TbUserMapperExt tbUserMapperExt;
    @Autowired
    TbUserRoleMapper tbUserRoleMapper;

    /**
     * @param userId
     * @param level
     */
    @Override
    @Transactional
    public void bindingUser(String userId, Integer level) {
        TbUserRole tbUserRole = new TbUserRole();
        tbUserRole.setUserId(Integer.valueOf(userId));
        TbUserRole checkResult = tbUserRoleMapper.selectOne(tbUserRole);
        if(null != checkResult){
            throw new RuntimeException("用户已经绑定过!");
        }

        TbUser tbUser = new TbUser();
        tbUser.setUserId(userId);
        tbUser.setLevel(level);
        tbUserMapperExt.updateByPrimaryKeySelective(tbUser);

        //改变user_role 表
        tbUserRole.setRoleId(level);
        tbUserRoleMapper.insert(tbUserRole);
    }

    @Override
    public void updatename(String userId, String username) {
        TbUser tbUser = new TbUser();
        tbUser.setUserId(userId);
        tbUser.setUsername(username);
        tbUserMapperExt.updateByPrimaryKeySelective(tbUser);
    }
}
