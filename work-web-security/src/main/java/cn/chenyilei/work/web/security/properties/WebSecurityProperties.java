package cn.chenyilei.work.web.security.properties;

import cn.chenyilei.work.web.security.filter.AuthenticationType;
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
@ConfigurationProperties(prefix = "cyl.security")
public class WebSecurityProperties {
    private boolean enabledSwagger = true ;
    private String loginPage = "/index.html"; //默认登陆页面
    private String loginPath = "/authentication/login/*";
    //认证类型
    private AuthenticationType authenticationType = AuthenticationType.none;

    private boolean permitAllWhenNoFilter = true;
    private String tokenHeader = "Authorization";


}
