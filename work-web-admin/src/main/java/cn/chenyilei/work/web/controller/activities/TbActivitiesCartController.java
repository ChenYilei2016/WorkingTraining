package cn.chenyilei.work.web.controller.activities;


import cn.chenyilei.work.domain.dto.ActivitiesCartRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.pojo.activities.TbActivitiesCart;
import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.TbActivitiesCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/24 16:02
 */
@Api(tags = "TbActivitiesCartController 活动购物车相关接口")
@RestController
@RequestMapping("/activitiescart")
public class TbActivitiesCartController {

    @Autowired
    TbActivitiesCartService tbActivitiesCartService;


    @ApiOperation("查询自己的购物车")
    @GetMapping("/selectMyCart")
    public AjaxResult<List<TbActivitiesCart>> selectMyCart(ActivitiesCartRequestParam param, PageRequest pageRequest){
        List<TbActivitiesCart> tbActivitiesCarts = tbActivitiesCartService.selectMyCart(param,pageRequest);
        return AjaxResult.success(tbActivitiesCarts,"查询成功!");
    }


    @ApiOperation("加入活动购物车")
    @PostMapping("/insertCartOne")
    public AjaxResult<Integer> insertCartOne(@RequestBody ActivitiesCartRequestParam.AcInsertCartOne param){
        //重复添加等暂时不弄
        Integer cartId = tbActivitiesCartService.insertCartOne(param);
        return AjaxResult.success(cartId,"加入成功!");
    }


    @ApiOperation("删除购物车中一件商品")
    @DeleteMapping("/delete/{activityId}")
    public AjaxResult deleteOne(@PathVariable("activityId")Integer activityId){
        tbActivitiesCartService.deleteCart(activityId);
        return AjaxResult.success(null,"删除成功!");
    }


    //数量为0删除购物车
    @ApiOperation("改变购物车一件商品的数量")
    @PutMapping("/update")
    public AjaxResult updateOne(@RequestBody ActivitiesCartRequestParam.AcUpdateOne param){
        tbActivitiesCartService.update(param);
        return AjaxResult.success(null,"更新成功!");
    }

}
