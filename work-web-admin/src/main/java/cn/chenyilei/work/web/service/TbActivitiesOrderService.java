package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.TbOrderDto;
import cn.chenyilei.work.domain.pojo.activities.TbActivitiesOrder;
import cn.chenyilei.work.domain.pojo.activities.ext.TbActivitiesOrderExt;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/29 15:32
 */
public interface TbActivitiesOrderService {
    Integer createOrder(TbOrderDto tbOrderDto);

    void payOrder(TbOrderDto tbOrderDto);

    List<TbActivitiesOrder> selectMyOrders(PageRequest pageRequest);

    TbActivitiesOrderExt queryOrderById(Integer orderId);
}
