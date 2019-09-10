package cn.chenyilei.work.web.security.constant.internal;

import lombok.Data;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 17:44
 */
@Data
public class SslProperties {
    private boolean enabledSsl  = false;
    private String keyStore     = "";
    private String keyPassword  = "";
    private String keyStoreType = "";
}
