package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.mapper.*;
import cn.chenyilei.work.domain.pojo.activities.TbActivitiesOrder;
import cn.chenyilei.work.domain.pojo.land.TbBindUserLand;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.pojo.land.TbLandOrder;
import cn.chenyilei.work.domain.pojo.land.TbPlanting;
import cn.chenyilei.work.domain.pojo.land.ext.TbBindUserLandVo;
import cn.chenyilei.work.domain.pojo.user.TbUser;
import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.FileService;
import cn.chenyilei.work.web.service.TbPlantingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员是无敌的
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/10/30 18:33
 */
@Api(tags = "管理员专用的接口!")
@RestController
@RequestMapping("/admin")
public class AdminControllerExt {
    @Autowired
    TbPlantingMapper tbPlantingMapper;
    @Autowired
    TbUserMapper tbUserMapper;
    @Autowired
    TbLandOrderMapper tbLandOrderMapper;
    @Autowired
    TbActivitiesOrderMapper tbActivitiesOrderMapper;

    @Autowired
    TbBindUserLandMapper tbBindUserLandMapper;

    @Autowired
    FileService fileService;

    @ApiOperation("查询所有种植信息")
    @GetMapping("/planting/selectAll")
    public AjaxResult<List<TbPlanting>> planting_selectAll(PageRequest pageRequest, Boolean isCustomer){
        List<TbPlanting> tbPlantings = tbPlantingMapper.selectAll();
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbPlantings)
                .pageRequest(pageRequest);
    }

    @ApiOperation("查询所有用户信息")
    @GetMapping("/user/selectAll")
    public AjaxResult<List<TbUser>> user_selectAll(PageRequest pageRequest){
        List<TbUser> tbUsers = tbUserMapper.selectAll();
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbUsers)
                .pageRequest(pageRequest);
    }

    @ApiOperation("所有的土地订单")
    @GetMapping("/landorder/selectAll")
    public AjaxResult<List<TbLandOrder>> landorder_selectAll(PageRequest pageRequest){
        List<TbLandOrder> tbLandOrders = tbLandOrderMapper.selectAll();
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbLandOrders)
                .pageRequest(pageRequest);
    }

    @ApiOperation("所有的活动订单")
    @GetMapping("/activitiesorder/selectAll")
    public AjaxResult<List<TbActivitiesOrder>> activitiesorder_selectAll(PageRequest pageRequest){
        List<TbActivitiesOrder> tbActivitiesOrders = tbActivitiesOrderMapper.selectAll();
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbActivitiesOrders)
                .pageRequest(pageRequest);
    }

    @ApiOperation("所有的用户土地关系")
    @GetMapping("/bind_land_user/selectAll")
    public AjaxResult<List<TbBindUserLand>> bind_land_user_selectAll(PageRequest pageRequest){
        List<TbBindUserLandVo> tbBindUserLandVos = tbBindUserLandMapper.selectVoAll();
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbBindUserLandVos)
                .pageRequest(pageRequest);
    }

}
