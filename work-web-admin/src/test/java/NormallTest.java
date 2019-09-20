import cn.chenyilei.work.domain.constant.TbRoleEnum;
import cn.chenyilei.work.domain.mapper.TbUserMapper;
import cn.chenyilei.work.domain.pojo.TbUser;
import cn.chenyilei.work.web.WebAdminApplication;
import cn.chenyilei.work.web.mapper.TbUserMapperSecurity;
import cn.chenyilei.work.web.security.processor.wx.WxUserDetailService;
import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.digest.BCrypt;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    String f = "C:\\Users\\Administrator\\Desktop\\School\\WorkingTraining\\images\\0\\0d43f08598313c178ef966fbdec9f36a.bmp";

    @Test
    public void login() throws FileNotFoundException {
        System.out.println(BCrypt.hashpw("123456"));

    }

}

