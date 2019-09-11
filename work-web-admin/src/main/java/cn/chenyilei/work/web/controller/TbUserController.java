package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.domain.pojo.TbUser;
import cn.chenyilei.work.web.service.impl.TbTbUserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:31
 */
@RequestMapping("/user")
@Controller
public class TbUserController extends CommonController<TbUser, TbTbUserServiceImpl>{

}
