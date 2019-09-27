import cn.chenyilei.work.domain.mapper.TbUserMapper;
import cn.chenyilei.work.domain.mapper.TbUserRoleMapper;
import cn.chenyilei.work.domain.pojo.user.TbUser;
import cn.chenyilei.work.domain.pojo.user.TbUserRole;
import cn.chenyilei.work.domain.pojo.internal_enum.UserLevelEnum;
import cn.chenyilei.work.web.WebAdminApplication;
import cn.chenyilei.work.web.mapper.TbUserMapperSecurity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 16:28
 */
@SpringBootTest(classes = {WebAdminApplication.class} )
@RunWith(SpringRunner.class)
public class TbUser操作Test {
    @Autowired
    TbUserMapperSecurity tbUserMapperSecurity;

    @Autowired
    TbUserRoleMapper tbUserRoleMapper;

    @Autowired
    TbUserMapper tbUserMapper;

    //测试账号
    private String wxopenid = "ok-r15ZJ_yeYQ67yWvdiJzJd_4uc";
    private String userId = "1";

    @Test
    public void 让账号变成无任何权限(){
        让账号变成无任何权限service();
    }


    @Test
    public void 让账号变成管理员权限(){
        TbUser tbUser = new TbUser();
        tbUser.setUserId("1");
        tbUser.setLevel(UserLevelEnum.ADMIN);
        tbUserMapper.updateByPrimaryKeySelective(tbUser);

        TbUserRole tbUserRole = new TbUserRole();
        tbUserRole.setUserId(1);
        tbUserRole.setRoleId(UserLevelEnum.ADMIN.getRoleId());
        tbUserRoleMapper.insert(tbUserRole);
    }





    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void 让账号变成无任何权限service(){
        TbUser tbUser = new TbUser();
        tbUser.setWxopenid(wxopenid);
        TbUser tbUser1 = tbUserMapper.selectOne(tbUser);

        Assert.assertNotNull("Tbuser为null",tbUser1);
        String userId = tbUser1.getUserId();

        //删除关联表
        TbUserRole tbUserRole = new TbUserRole();
        tbUserRole.setUserId(Integer.valueOf(userId));
        tbUserRoleMapper.delete(tbUserRole);

        //设置显示标志level = 0
        tbUser1.setLevel(UserLevelEnum.NONE);
        tbUserMapper.updateByPrimaryKeySelective(tbUser1);
        System.err.println("!11111111");
    }
}