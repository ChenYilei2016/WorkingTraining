package cn.chenyilei.work.web.security.filter;

import cn.chenyilei.work.web.security.filter.normal.NormalFilterConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 可以选择性配置额外的过滤器
 * @see {@link NormalFilterConfiguration}
 * @see {@link cn.chenyilei.work.web.security.filter.jwt.JwtFilterConfiguration}
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/10 14:00
 */
public interface FilterConfiguration {
    public void filterConfig(HttpSecurity http) throws Exception;
}
