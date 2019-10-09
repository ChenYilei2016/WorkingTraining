package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.TbOrderDto;
import cn.chenyilei.work.domain.pojo.land.TbLandOrder;
import cn.chenyilei.work.domain.pojo.land.ext.TbLandOrderExt;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 19:09
 */
public interface TbLandOrderService {
    Integer createOrder(TbOrderDto tbOrderDto);

    void payOrder(TbOrderDto tbOrderDto);

    TbLandOrderExt queryOrderById(Integer orderId);

    List<TbLandOrder> selectMyOrders(PageRequest pageRequest);
}
