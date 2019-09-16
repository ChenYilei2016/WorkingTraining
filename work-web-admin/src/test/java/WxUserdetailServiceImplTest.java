import cn.chenyilei.work.domain.pojo.TbUser;
import cn.chenyilei.work.web.WebAdminApplication;
import cn.chenyilei.work.web.security.processor.wx.WxUserdetailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
    WxUserdetailServiceImpl wxUserdetailService;
    @Autowired
    JdbcTemplate jdbcTemplate;

//    TbUser tbUser = new TbUser();
//        tbUser.setWxopenid(openid);
//    TbUser selectUser = tbUserMapper.selectOne(tbUser);
//        if(selectUser == null){
//        //注册
//        tbUser.setUsername(openid);
//        //自动带上附赠ID
//        int insert = tbUserMapper.insert(tbUser);
//        return tbUser;
//    }
//        return selectUser;
//}

    @Test
    public void login() {
        String openid = "123";
//        TbUser tbUser = jdbcTemplate.queryForObject(
//                "select * from tb_user where wxopenid = ?",
//                new Object[]{openid},
//                new BeanPropertyRowMapper<TbUser>(TbUser.class)
//        );
//
//        if(null == tbUser){
//            tbUser.setUsername(openid);
//            tbUser.setWxopenid(openid);
            jdbcTemplate.update("insert into tb_user (username,wxopenid) values (?,?)",openid,openid);
//        }

    }
}