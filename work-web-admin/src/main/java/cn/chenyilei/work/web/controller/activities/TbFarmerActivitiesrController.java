package cn.chenyilei.work.web.controller.activities;

import cn.chenyilei.work.domain.dto.ActivitiesQueryParam;
import cn.chenyilei.work.domain.dto.LandRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.TbActivitiesService;
import cn.chenyilei.work.web.service.TbLandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @see cn.chenyilei.work.domain.pojo.activities.TbActivities
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 13:46
 */
@Api(tags = "TbFarmerActivitiesrController 农户活动相关接口")
@RestController
@RequestMapping("/farmer/activities")
public class TbFarmerActivitiesrController {

    @Autowired
    TbActivitiesService tbActivitiesService;

    @ApiOperation("查询固定ID的活动")
    @GetMapping("/select/{activitiesId}")
    public AjaxResult<TbActivities> selectOne(
            @PathVariable("activitiesId") Integer activitiesId, ActivitiesQueryParam param){
        TbActivities tbActivities = tbActivitiesService.selectOneById(activitiesId,param);
        return AjaxResult.success(tbActivities,"查询成功!");
    }

    @ApiOperation("查询自己的所有活动!")
    @GetMapping("/selectMyList")
    public AjaxResult<List<TbActivities>> selectMyList(ActivitiesQueryParam param,PageRequest pageRequest){
        List<TbActivities> activities = tbActivitiesService.selectFarmerList(param,pageRequest);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(activities)
                .pageRequest(pageRequest);
    }

    @ApiOperation("农户发布自己的活动!")
    @PostMapping("/register")
    public AjaxResult<Integer> registerActivities(@RequestBody TbActivities tbActivities){
        tbActivities.setActivitiesStatus(CheckEnum.SUCCESS);
        Integer activityId = tbActivitiesService.registerOneActivitie(tbActivities);
        return AjaxResult.success(activityId,"发布活动成功!");
    }


    @ApiOperation("农民删除自己的活动!,如果有人已经买了,联系管理员")
    @DeleteMapping("/delete/{activitiesId}")
    public AjaxResult deleteActivities(@PathVariable("activitiesId")Integer activitiesId){
        //已经被租的地不能删除!
        tbActivitiesService.deleteActivities(activitiesId);
        return AjaxResult.success(null,"删除活动成功!");
    }


    @ApiOperation("农民修改自己的活动")
    @PutMapping("/update")
    public AjaxResult updateActivities(@RequestBody TbActivities tbActivities){
        tbActivitiesService.updateActivities(tbActivities);
        return AjaxResult.success(null,"更新活动成功!");
    }

}

