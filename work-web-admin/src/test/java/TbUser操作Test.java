import cn.chenyilei.work.domain.mapper.TbUserMapper;
import cn.chenyilei.work.domain.mapper.TbUserRoleMapper;
import cn.chenyilei.work.domain.pojo.TbUser;
import cn.chenyilei.work.domain.pojo.TbUserRole;
import cn.chenyilei.work.web.WebAdminApplication;
import cn.chenyilei.work.web.mapper.TbUserMapperSecurity;
import cn.chenyilei.work.web.security.processor.wx.WxUserDetailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collection;

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
    public void test(){
        TbUser tbUser = new TbUser();
        tbUser.setUserId("1");
        tbUser.setLevel(0);
        tbUserMapper.updateByPrimaryKeySelective(tbUser);
    }

    @Test
    public void 让账号变成无任何权限(){
        让账号变成无任何权限service();
    }


    @Test
    public void 让账号变成管理员权限(){
        TbUser tbUser = new TbUser();
        tbUser.setUserId("1");
        tbUser.setLevel(1);
        tbUserMapper.updateByPrimaryKeySelective(tbUser);

        TbUserRole tbUserRole = new TbUserRole();
        tbUserRole.setUserId(1);
        tbUserRole.setRoleId(1);
        tbUserRoleMapper.insert(tbUserRole);
    }





    @Transactional
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
        tbUser1.setLevel(0);
        tbUserMapper.updateByPrimaryKeySelective(tbUser1);
        System.err.println("!11111111");
    }
}