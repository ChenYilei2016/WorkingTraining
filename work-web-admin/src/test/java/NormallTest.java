import cn.hutool.crypto.digest.BCrypt;
import org.junit.Test;

import java.io.FileNotFoundException;

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

