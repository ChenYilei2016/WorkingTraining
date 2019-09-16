package cn.chenyilei.work.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 17:44
 */
@Data
@ConfigurationProperties(prefix = "cyl.ssl")
@Component
public class SslProperties {
    private boolean enabledSsl  = false;
    private String keyStore     = "";
    private String keyPassword  = "";
    private String keyStoreType = "";
}
