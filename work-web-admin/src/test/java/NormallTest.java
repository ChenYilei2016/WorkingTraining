import cn.chenyilei.work.domain.constant.TbRoleEnum;
import cn.chenyilei.work.domain.mapper.TbUserMapper;
import cn.chenyilei.work.domain.pojo.TbUser;
import cn.chenyilei.work.web.WebAdminApplication;
import cn.chenyilei.work.web.mapper.TbUserMapperSecurity;
import cn.chenyilei.work.web.security.processor.wx.WxUserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 16:28
 */

public class NormallTest {


    @Test
    public void login() {
        TbRoleEnum role = TbRoleEnum.getRole(1);
    }

}

