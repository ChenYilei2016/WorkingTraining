import cn.chenyilei.work.web.WebAdminApplication;
import cn.chenyilei.work.web.security.processor.wx.WxUserdetailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void login() {
        System.out.println(wxUserdetailService.login("123123aa"));
    }
}