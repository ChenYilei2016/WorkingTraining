package cn.chenyilei.work.web.controller.activities;

import cn.chenyilei.work.domain.dto.LandRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.TbLandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "TbActivitiesController 活动相关接口")
@RestController
@RequestMapping("/activities")
public class TbActivitiesFarmerController {


//    @ApiOperation("查询固定ID的田")
//    @GetMapping("/select/{landId}")
//    public AjaxResult<TbLand> selectOne(@PathVariable("landId") Integer landId
//            ,LandRequestParam landRequestParam, PageRequest pageRequest){
//        TbLand tbLand = tbLandService.selectOne(landId,landRequestParam,pageRequest);
//        return AjaxResult.success(tbLand,"查询成功!");
//    }
//
//    @ApiOperation("查询所有的田")
//    @GetMapping("/selectAll")
//    public AjaxResult<List<TbLand>> selectList(LandRequestParam landRequestParam, PageRequest pageRequest){
//        List<TbLand> tbLands = tbLandService.selectAll(landRequestParam,pageRequest);
//        return AjaxPageResult
//                .builder()
//                .success(true)
//                .msg("查询成功!")
//                .data(tbLands)
//                .pageRequest(pageRequest);
//    }
//
//    @ApiOperation("查询自己的田!")
//    @GetMapping("/selectFarmerList")
//    public AjaxResult<List<TbLand>> selectFarmerList(LandRequestParam landRequestParam,PageRequest pageRequest){
//        List<TbLand> tbLands = tbLandService.selectFarmerList(landRequestParam,pageRequest);
//        return AjaxPageResult
//                .builder()
//                .success(true)
//                .msg("查询成功!")
//                .data(tbLands)
//                .pageRequest(pageRequest);
//    }
//
//

    @ApiOperation("农户发布自己的活动!")
    @PostMapping("/register")
    public AjaxResult registerLand(@RequestBody TbActivities tbActivities){
        
        return AjaxResult.success(null,"注册田地成功!");
    }

//
//    @ApiOperation("农民删除自己的田!")
//    @DeleteMapping("/delete/{landId}")
//    public AjaxResult deleteLand(@PathVariable("landId")Integer landId){
//        //已经被租的地不能删除!
//        tbLandService.deleteLand(landId);
//        return AjaxResult.success(null,"注册田地成功!");
//    }
//
//    @ApiOperation("农民修改自己的田!")
//    @PutMapping("/update")
//    public AjaxResult updateLand(@RequestBody TbLand tbLand){
//        tbLandService.updateLand(tbLand);
//        return AjaxResult.success(null,"注册田地成功!");
//    }

}

