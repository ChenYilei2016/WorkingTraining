package cn.chenyilei.work.web.controller.activities;

import cn.chenyilei.work.domain.dto.ActivitiesQueryParam;
import cn.chenyilei.work.domain.dto.LandRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.mapper.TbActivitiesMapper;
import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.TbActivitiesService;
import cn.chenyilei.work.web.service.TbLandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 13:46
 */
@Api(tags = "TbCustomerActivitiesController 客户活动相关接口")
@RestController
@RequestMapping("/customer/activities")
public class TbCustomerActivitiesController {

    @Autowired
    TbActivitiesService tbActivitiesService;


    @ApiOperation("查询固定ID的活动")
    @GetMapping("/select/{activitiesId}")
    public AjaxResult<TbActivities> selectOne(
            @PathVariable("activitiesId") Integer activitiesId, ActivitiesQueryParam param){
        TbActivities tbActivities = tbActivitiesService.selectOneById(activitiesId,param);
        return AjaxResult.success(tbActivities,"查询成功!");
    }


    @ApiOperation("客户查询所有的活动")
    @GetMapping("/selectAll")
    public AjaxResult<List<TbActivities>> selectAll(ActivitiesQueryParam param, PageRequest pageRequest){
        //客户看审核成功的活动
        param.setActivitiesStatus(CheckEnum.SUCCESS);
        List<TbActivities> tbActivities = tbActivitiesService.selectAll(param,pageRequest);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbActivities)
                .pageRequest(pageRequest);
    }


    @ApiOperation("客户查询自己的活动!")
    @GetMapping("/selectMyList")
    public AjaxResult<List<TbActivities>> selectMyList(ActivitiesQueryParam param,PageRequest pageRequest){
        List<TbActivities> tbActivities = tbActivitiesService.selectCustomerList(param,pageRequest);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbActivities)
                .pageRequest(pageRequest);
    }



}

