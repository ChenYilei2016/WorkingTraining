package cn.chenyilei.work.web.security.filter;

import cn.chenyilei.work.web.security.filter.jwt.JwtFilterConfiguration;
import cn.chenyilei.work.web.security.filter.normal.NormalFilterConfiguration;

/**
 * 使用的验证过滤器配置
 * @see {@link NormalFilterConfiguration}
 * @see {@link JwtFilterConfiguration}
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 18:23
 */
public enum AuthenticationType {
    none,
    normal,
    jwt
}
