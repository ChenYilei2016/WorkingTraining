package cn.chenyilei.work.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/19 14:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "cyl.system")
public class SystemProperties {
    /**
     * 上传图片路径的位置前缀
     */
    private String uploadImgDirPerfix = "C:/Users/Administrator/Desktop/School/WorkingTraining/images/";
    /**
     * 访问图片路径的前缀!
     */
    private String visitImgDirPerfix = "https://chenyilei.mynatapp.cc/image/";
}
