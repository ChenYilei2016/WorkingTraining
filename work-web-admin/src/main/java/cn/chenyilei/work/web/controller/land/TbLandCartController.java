package cn.chenyilei.work.web.controller.land;

import cn.chenyilei.work.domain.mapper.TbLandCartMapper;
import cn.chenyilei.work.domain.pojo.land.TbLandCart;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.security.SecurityContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO: 未实现
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/24 16:02
 */
@Api("田地购物车")
@RestController
@RequestMapping("/landcart")
public class TbLandCartController {
    @Autowired
    TbLandCartMapper tbLandCartMapper;

    @ApiOperation("查询自己的购物车")
    @GetMapping("/selectMyCart")
    public AjaxResult selectMyCart(){
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        TbLandCart tbLandCart = new TbLandCart();
        tbLandCart.setUserId(Integer.valueOf(user.getUserId()));
        List<TbLandCart> result = tbLandCartMapper.select(tbLandCart);

        return null;
    }

    @ApiOperation("加入田地购物车")
    public AjaxResult insertCartOne(){

        return null;
    }

    @ApiOperation("删除购物车中一件商品")
    public AjaxResult deleteOne(){

        return null;
    }


    @ApiOperation("改变购物车一件商品的数量")
    public AjaxResult updateOne(){

        return null;
    }

}
