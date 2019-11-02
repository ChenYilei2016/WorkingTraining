package cn.chenyilei.work.web.controller.land;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.dto.LandCartRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.TbOrderDto;
import cn.chenyilei.work.domain.pojo.land.TbLandOrder;
import cn.chenyilei.work.domain.pojo.land.ext.TbLandOrderExt;
import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.exception.InvalidDoException;
import cn.chenyilei.work.web.exception.OrderException;
import cn.chenyilei.work.web.service.TbLandOrderService;
import cn.chenyilei.work.web.service.TblandCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/24 15:59
 */
@Api(tags = "TbLandOrderController 田地订单相关接口!")
@RestController
@RequestMapping("/landorder")
public class TbLandOrderController {
    @Autowired
    TbLandOrderService tbLandOrderService;
    @Autowired
    TblandCartService tblandCartService;

    /**
     *  创建订单 返回订单号
     * @see cn.chenyilei.work.domain.pojo.internal_enum.OrderStatusEnum
     */
    @ApiOperation("创建订单")
    @PostMapping("/createOrder")
    @Transactional
    public AjaxResult<Integer> createOrder(@RequestBody TbOrderDto tbOrderDto){
        if(null != tbOrderDto.getProduceId() ){
            //将商品转成购物车直接进行下单
            LandCartRequestParam.InsertCartOne param = new LandCartRequestParam.InsertCartOne();
            param.setLandId(tbOrderDto.getProduceId());
            param.setNumber(tbOrderDto.getProduceNumber());
            Integer cartId = tblandCartService.insertCartOne(param);
            tbOrderDto.setCartIds(Arrays.asList(cartId));
        }
        Integer orderId = tbLandOrderService.createOrder(tbOrderDto);
        return AjaxResult.success(orderId,"创建订单成功!");
    }

    /**
     * 需要notify来更改订单
     * @return
     */
    @ApiOperation("订单付款")
    @PostMapping("/payOrder")
    public AjaxResult payOrder(@RequestBody TbOrderDto tbOrderDto){
        if(tbOrderDto.getOrderId() == null){
            throw new OrderException(CodeResultEnum.INVALID_PARAM);
        }
        tbLandOrderService.payOrder(tbOrderDto);
        return AjaxResult.success(null,"支付中!");
    }

    @ApiOperation("查询客户具有的订单列表")
    @GetMapping("/selectMyList")
    public AjaxResult<List<TbLandOrder>> queryOrdersNoDetail(PageRequest pageRequest){
        List<TbLandOrder> tbLandOrders = tbLandOrderService.selectMyOrders(pageRequest);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbLandOrders)
                .pageRequest(pageRequest);
    }

//    //TODO:
//    @ApiOperation("查询客户具有的订单详情列表")
//    @GetMapping("/selectMyDetailList")
//    public AjaxResult<List<TbLandOrderExt>> queryOrdersWithDetail(PageRequest pageRequest){
//        List<TbLandOrderExt> tbLandOrders = tbLandOrderService.selectMyOrders(pageRequest);
//        return AjaxPageResult
//                .builder()
//                .success(true)
//                .msg("查询成功!")
//                .data(tbLandOrders)
//                .pageRequest(pageRequest);
//    }

    @ApiOperation("查询客户具有的订单和详情")
    @GetMapping("/select/{orderId}")
    public AjaxResult<TbLandOrderExt> queryOrderById(@PathVariable("orderId") Integer orderId){
        TbLandOrderExt tbLandOrderExt = tbLandOrderService.queryOrderById(orderId);
        return AjaxResult.success(tbLandOrderExt,"查询成功!");
    }


}
