import cn.chenyilei.work.domain.mapper.TbUserMapper;
import cn.chenyilei.work.domain.pojo.user.TbPermission;
import cn.chenyilei.work.domain.pojo.user.TbUser;
import cn.chenyilei.work.web.WebAdminApplication;
import cn.chenyilei.work.web.mapper.TbUserMapperSecurity;
import cn.chenyilei.work.web.security.processor.wx.WxUserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 16:28
 */
@SpringBootTest(classes = {WebAdminApplication.class} )
@RunWith(SpringRunner.class)
public class WxUserdetailServiceImplTest {

    @Autowired
    WxUserDetailService wxUserDetailService;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TbUserMapper tbUserMapper;

    @Autowired
    TbUserMapperSecurity tbUserMapperSecurity;

    @Test
    public void login() {
        String openid = "12344122";
        TbUser tbUser = new TbUser();
        tbUser.setWxopenid(openid);
        TbUser selectUser = tbUserMapper.selectOne(tbUser);
        if(selectUser == null){
            //注册
            tbUser.setUsername(openid);
            //自动带上附赠ID
            int insert = tbUserMapper.insert(tbUser);
        }
        System.out.println("1");
    }

    public void test (String ... a){

    }
    @Test
    public void normaluser() {
        List<TbPermission> permissionsByUserId = tbUserMapperSecurity.findPermissionsByUserId("1");
        System.out.println(permissionsByUserId);
    }
}