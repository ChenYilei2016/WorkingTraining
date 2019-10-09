package cn.chenyilei.work.web.controller.activities;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.dto.ActivitiesCartRequestParam;
import cn.chenyilei.work.domain.dto.LandCartRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.TbOrderDto;
import cn.chenyilei.work.domain.pojo.activities.TbActivitiesOrder;
import cn.chenyilei.work.domain.pojo.activities.ext.TbActivitiesOrderExt;
import cn.chenyilei.work.domain.pojo.land.TbLandOrder;
import cn.chenyilei.work.domain.pojo.land.ext.TbLandOrderExt;
import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.exception.OrderException;
import cn.chenyilei.work.web.service.TbActivitiesCartService;
import cn.chenyilei.work.web.service.TbActivitiesOrderService;
import cn.chenyilei.work.web.service.TbLandOrderService;
import cn.chenyilei.work.web.service.TblandCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "TbActivitiesOrderController 活动订单相关接口!")
@RestController
@RequestMapping("/activitiesorder")
public class TbActivitiesOrderController {

    @Autowired
    TbActivitiesCartService tbActivitiesCartService;

    @Autowired
    TbActivitiesOrderService tbActivitiesOrderService;

    /**
     *  创建订单 返回订单号
     * @see cn.chenyilei.work.domain.pojo.internal_enum.OrderStatusEnum
     */
    @ApiOperation("创建活动订单")
    @PostMapping("/createOrder")
    @Transactional
    public AjaxResult<Integer> createOrder(@RequestBody TbOrderDto tbOrderDto){
        if(null != tbOrderDto.getProduceId() ){
            //将商品转成购物车直接进行下单
            ActivitiesCartRequestParam.AcInsertCartOne param = new ActivitiesCartRequestParam.AcInsertCartOne();
            param.setActivityId(tbOrderDto.getProduceId());
            param.setNumber(1);
            Integer cartId = tbActivitiesCartService.insertCartOne(param);
            tbOrderDto.setCartIds(Arrays.asList(cartId));
        }
        Integer orderId = tbActivitiesOrderService.createOrder(tbOrderDto);
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
        tbActivitiesOrderService.payOrder(tbOrderDto);
        return AjaxResult.success(null,"支付中!");
    }


    @ApiOperation("查询客户具有的订单列表")
    @GetMapping("/selectMyList")
    public AjaxResult<List<TbActivitiesOrder>> queryOrdersNoDetail(PageRequest pageRequest){
        List<TbActivitiesOrder> tbActivitiesOrders = tbActivitiesOrderService.selectMyOrders(pageRequest);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbActivitiesOrders)
                .pageRequest(pageRequest);
    }


    @ApiOperation("查询客户具有的订单和详情")
    @GetMapping("/select/{orderId}")
    public AjaxResult<TbActivitiesOrderExt> queryOrderById(@PathVariable("orderId") Integer orderId){
        TbActivitiesOrderExt tbActivitiesOrderExt = tbActivitiesOrderService.queryOrderById(orderId);
        return AjaxResult.success(tbActivitiesOrderExt,"查询成功!");
    }


}
