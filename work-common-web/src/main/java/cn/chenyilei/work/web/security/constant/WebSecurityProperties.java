package cn.chenyilei.work.web.security.constant;

import cn.chenyilei.work.web.security.constant.internal.SslProperties;
import cn.chenyilei.work.web.security.filter.AuthenticationType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

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
    private String loginPath = "/authentication/login/*";
    //认证类型
    private AuthenticationType authenticationType = AuthenticationType.none;

    private boolean permitAllWhenNoFilter = true;
    private String tokenHeader = "Authorization";

    @NestedConfigurationProperty
    private SslProperties ssl = new SslProperties();



}
