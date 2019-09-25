package cn.chenyilei.work.web.controller.land;

import cn.chenyilei.work.domain.dto.LandCartRequestParam;
import cn.chenyilei.work.domain.pojo.land.TbLandCart;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.TblandCartService;
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
@Api(tags = "TbLandCartController 田地购物车相关接口")
@RestController
@RequestMapping("/landcart")
public class TbLandCartController {

    @Autowired
    TblandCartService tblandCartService;


    @ApiOperation("查询自己的购物车")
    @GetMapping("/selectMyCart")
    public AjaxResult selectMyCart(LandCartRequestParam landCartRequestParam){
        List<TbLandCart> tbLandCartList = tblandCartService.selectMyCart(landCartRequestParam);
        return AjaxResult.success(tbLandCartList,"查询成功!");
    }

    @ApiOperation("加入田地购物车")
    @PostMapping("/insertCartOne")
    public AjaxResult insertCartOne(@RequestBody LandCartRequestParam.InsertCartOne param){
        //重复添加等暂时不弄
        tblandCartService.insertCartOne(param);
        return AjaxResult.success(null,"加入成功!");
    }

    @ApiOperation("删除购物车中一件商品")
    @DeleteMapping("/delete/{cartId}")
    public AjaxResult deleteOne(@PathVariable("cartId")Integer cartId){
        tblandCartService.deleteCart(cartId);
        return AjaxResult.success(null,"删除成功!");
    }


    //租天数不考虑库存
    @ApiOperation("改变购物车一件商品的数量")
    @PutMapping("/update")
    public AjaxResult updateOne(@RequestBody LandCartRequestParam.UpdateOne param){
        tblandCartService.update(param);
        return AjaxResult.success(null,"更新成功!");
    }

}
