package cn.chenyilei.work.web.controller.land;

import cn.chenyilei.work.domain.dto.LandRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;
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
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 13:46
 */
@Api(tags = "TbFarmerLandController 农户田地相关接口")
@RestController
@RequestMapping("/farmer/land")
public class TbFarmerLandController {

    @Autowired
    TbLandService tbLandService;

    @ApiOperation("查询固定ID的田")
    @GetMapping("/select/{landId}")
    public AjaxResult<TbLand> selectOne(@PathVariable("landId") Integer landId
            ,LandRequestParam landRequestParam){
        TbLand tbLand = tbLandService.selectOne(landId,landRequestParam,null);
        return AjaxResult.success(tbLand,"查询成功!");
    }


    @ApiOperation("农户查询自己的田!")
    @GetMapping("/selectMyList")
    public AjaxResult<List<TbLand>> selectMyList(LandRequestParam landRequestParam,PageRequest pageRequest){
        //农户自己可以查询所有的田,包括未审核的
        List<TbLand> tbLands = tbLandService.selectFarmerList(landRequestParam,pageRequest);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbLands)
                .pageRequest(pageRequest);
    }


    @ApiOperation("农民注册自己的田!")
    @PostMapping("/register")
    public AjaxResult registerLand(@RequestBody TbLand tbLand){
        tbLand.setLandStatus(CheckEnum.SUCCESS);
        tbLandService.registerLand(tbLand);
        return AjaxResult.success(null,"注册田地成功!");
    }

    @ApiOperation("农民删除自己的田!")
    @DeleteMapping("/delete/{landId}")
    public AjaxResult deleteLand(@PathVariable("landId")Integer landId){
        //已经被租的地不能删除!
        tbLandService.deleteLand(landId);
        return AjaxResult.success(null,"删除田地成功!");
    }

    @ApiOperation("农民修改自己的田!")
    @PutMapping("/update")
    public AjaxResult updateLand(@RequestBody TbLand tbLand){
        tbLandService.updateLand(tbLand);
        return AjaxResult.success(null,"更新田地成功!");
    }



}

