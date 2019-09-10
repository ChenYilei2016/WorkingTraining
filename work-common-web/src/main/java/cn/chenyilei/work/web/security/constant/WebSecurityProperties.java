package cn.chenyilei.work.web.security.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全相关常量
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/09 13:46
 */
@Data
@ConfigurationProperties(prefix = "cyl")
public class WebSecurityProperties {
    private boolean enabledSwagger ;
    private String loginPage = "/index.html"; //默认登陆页面
}
