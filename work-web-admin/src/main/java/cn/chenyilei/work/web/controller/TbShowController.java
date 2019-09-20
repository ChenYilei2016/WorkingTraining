package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.web.service.TbShowService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注释
 * @see cn.chenyilei.work.domain.pojo.TbShow
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/20 16:11
 */
@Api("风采展示接口")
@RestController
@RequestMapping("/show")
public class TbShowController {

    @Autowired
    TbShowService tbShowService;

//    @GetMapping("/selectlist/{page}/{pageSize}")
//    public AjaxPageResult selectlist(@PathVariable()){
//
//    }


}
