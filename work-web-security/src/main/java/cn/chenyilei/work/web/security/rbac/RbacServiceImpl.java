package cn.chenyilei.work.web.security.rbac;

import cn.chenyilei.work.domain.mapper.TbPermissionMapper;
import cn.chenyilei.work.domain.pojo.user.TbPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/14 12:02
 */
@Slf4j
@Component(value = "rbacService")
public class RbacServiceImpl implements InitializingBean,RbacService {
    @Autowired
    TbPermissionMapper tbPermissionMapper;

    private volatile boolean isInit = false;
    private ConcurrentHashMap<String,String> permissionMap = new ConcurrentHashMap<String,String>();

    /**
     * 判断是否匹配
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request,
                                 Authentication authentication) throws Exception{
//        log.error("hasPermission验证");
        /**
         * 获取 request 对应 permissionMap 中的权限
         * 和 authentication 中做比较
         */
        return true;
    }

    /**
     * 初始化权限
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if(!isInit){
            synchronized (getClass()){
                if(!isInit){
                    List<TbPermission> tbPermissions = tbPermissionMapper.selectAll();
                    tbPermissions.stream().forEach(tbPermission -> {
                        permissionMap.put(tbPermission.getPermissionName(),tbPermission.getPermissionUrl());
                    });
                    isInit = true;
                }
            }
        }
    }
}
