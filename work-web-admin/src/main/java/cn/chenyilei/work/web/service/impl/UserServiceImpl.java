package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.pojo.TbUser;
import cn.chenyilei.work.web.mapper.UserMapper;
import cn.chenyilei.work.web.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:40
 */
@Service
public class UserServiceImpl extends CommonServiceImpl<TbUser, UserMapper> implements UserService {

}
