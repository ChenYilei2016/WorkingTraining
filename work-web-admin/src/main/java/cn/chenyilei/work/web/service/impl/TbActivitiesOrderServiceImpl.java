package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.commonutils.MapUtils;
import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.TbOrderDto;
import cn.chenyilei.work.domain.mapper.*;
import cn.chenyilei.work.domain.pojo.activities.*;
import cn.chenyilei.work.domain.pojo.activities.ext.TbActivitiesOrderExt;
import cn.chenyilei.work.domain.pojo.internal_enum.OrderStatusEnum;
import cn.chenyilei.work.domain.pojo.land.*;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.exception.OrderException;
import cn.chenyilei.work.web.service.TbActivitiesOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/29 15:33
 */
@Service
public class TbActivitiesOrderServiceImpl implements TbActivitiesOrderService {
    public static final String ORDERBY_CREATETIME_DESC= "createtime desc";
    @Autowired
    TbActivitiesCartMapper tbActivitiesCartMapper;
    @Autowired
    TbActivitiesMapper tbActivitiesMapper;
    @Autowired
    TbActivitiesOrderMapper tbActivitiesOrderMapper;
    @Autowired
    TbActivitiesOrderDetailMapper tbActivitiesOrderDetailMapper;
    @Autowired
    TbBindUserActivitiesMapper tbBindUserActivitiesMapper;

    @Override
    @Transactional
    public Integer createOrder(TbOrderDto tbOrderDto) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();

        TbActivitiesOrder tbActivitiesOrder = new TbActivitiesOrder();
        tbActivitiesOrder.setCreatetime(new Date());
        tbActivitiesOrder.setUpdatetime(new Date());
        tbActivitiesOrder.setOrderstatus(OrderStatusEnum.NEW);
        tbActivitiesOrder.setBuyerId(Integer.valueOf(user.getUserId()));

        List<Integer> cartIds = tbOrderDto.getCartIds();
        List<TbActivitiesCart> tbActivitiesCarts = tbActivitiesCartMapper.selectByIdList(cartIds);
        List<Integer> activitiesIdList = tbActivitiesCarts.stream().map(x -> x.getActivitiesId()).collect(Collectors.toList());
        List<TbActivities> tbActivitiesList = tbActivitiesMapper.selectByIdList(activitiesIdList);

        if(tbActivitiesCarts.isEmpty()){
            throw new OrderException(CodeResultEnum.REPEATE);
        }

        Map<Integer, TbActivities> carts_tbActivities_MAP = MapUtils.toMap(cartIds, tbActivitiesList);

        //订单价格
        Integer totalPrice= 0;

        for (TbActivitiesCart tbActivitiesCart : tbActivitiesCarts) {

            TbActivities tbActivities = carts_tbActivities_MAP.get(tbActivitiesCart.getId());

            Integer updateCount = tbActivitiesMapper.lessNumber(tbActivities.getActivitiesId(), tbActivitiesCart.getNumber());
            if(  updateCount < 1  ){
                throw new OrderException(CodeResultEnum.REPEATE,tbActivitiesCart.getName()+"已经售光了!");
            }
            //计算总价
            totalPrice += tbActivities.getActivitiesPrice() * tbActivitiesCart.getNumber();
        }

        tbActivitiesOrder.setPrice(totalPrice);
        tbActivitiesOrderMapper.insertSelective(tbActivitiesOrder);

        //插入订单详情
        for (int i = 0 ; i < tbActivitiesCarts.size() ; i++){
            TbActivitiesCart tbActivitiesCart = tbActivitiesCarts.get(i);
            TbActivities tbActivities = carts_tbActivities_MAP.get(tbActivitiesCart.getId());

            TbActivitiesOrderDetail tbActivitiesOrderDetail = new TbActivitiesOrderDetail();
            tbActivitiesOrderDetail.setActivitiesId(tbActivities.getActivitiesId());
            tbActivitiesOrderDetail.setActivitiesImage(tbActivities.getActivitiesImage());
            tbActivitiesOrderDetail.setActivitiesName(tbActivities.getActivitiesName());
            tbActivitiesOrderDetail.setActivitiesPrice(tbActivities.getActivitiesPrice());
            tbActivitiesOrderDetail.setOrderId(tbActivitiesOrder.getId());
            tbActivitiesOrderDetail.setNumber(tbActivitiesCart.getNumber());
            tbActivitiesOrderDetailMapper.insert(tbActivitiesOrderDetail);
        }

        //删除购物车的内容
        tbActivitiesCartMapper.deleteByIdList(cartIds);
        return tbActivitiesOrder.getId();
    }

    @Override
    @Transactional
    public void payOrder(TbOrderDto tbOrderDto) {
        Integer orderId = tbOrderDto.getOrderId();
        TbActivitiesOrder tbActivitiesOrder = tbActivitiesOrderMapper.selectByPrimaryKey(orderId);

        if(tbActivitiesOrder.getOrderstatus() == OrderStatusEnum.PAYSUCCESS){
            throw new OrderException(CodeResultEnum.REPEATE,"已经支付过!");
        }

        //回调
        //TODO: 测试环境直接成功
        notifyPay(tbActivitiesOrder);
    }

    @Override
    public List<TbActivitiesOrder> selectMyOrders(PageRequest pageRequest) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        TbActivitiesOrder tbActivitiesOrder = new TbActivitiesOrder();
        tbActivitiesOrder.setBuyerId(Integer.valueOf(user.getUserId()));
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize(),ORDERBY_CREATETIME_DESC);
        List<TbActivitiesOrder> result = tbActivitiesOrderMapper.select(tbActivitiesOrder);
        return result;
    }

    @Override
    public TbActivitiesOrderExt queryOrderById(Integer orderId) {
        TbActivitiesOrderExt tbActivitiesOrderExt = new TbActivitiesOrderExt();
        TbActivitiesOrder tbActivitiesOrder = tbActivitiesOrderMapper.selectByPrimaryKey(orderId);
        BeanUtils.copyProperties(tbActivitiesOrder,tbActivitiesOrderExt);

        Example example =new Example(TbActivitiesOrderDetail.class);
        example.createCriteria().andEqualTo("orderId",orderId);
        List<TbActivitiesOrderDetail> tbActivitiesOrderDetails = tbActivitiesOrderDetailMapper.selectByExample(example);
        tbActivitiesOrderExt.setTbActivitiesOrderDetails(tbActivitiesOrderDetails);

        return tbActivitiesOrderExt;
    }

    private void notifyPay(TbActivitiesOrder order){
        //判断成功失败等

        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();

        order.setOrderstatus(OrderStatusEnum.PAYSUCCESS);
        tbActivitiesOrderMapper.updateByPrimaryKeySelective(order);

        //绑定商品
        TbActivitiesOrderDetail tbActivitiesOrderDetail= new TbActivitiesOrderDetail();
        tbActivitiesOrderDetail.setOrderId(order.getId());
        List<TbActivitiesOrderDetail> detailList = tbActivitiesOrderDetailMapper.select(tbActivitiesOrderDetail);

        for (TbActivitiesOrderDetail detail : detailList) {

            //绑定关系
            TbActivities tbActivities = tbActivitiesMapper.selectByPrimaryKey(detail.getActivitiesId());

            TbBindUserActivities tbBindUserActivities = new TbBindUserActivities();
            tbBindUserActivities.setUaBuyNumber(detail.getNumber());
            tbBindUserActivities.setUaActivitiesId(tbActivities.getActivitiesId());
            tbBindUserActivities.setUaSellUserId(tbActivities.getActivitiesUserId());
            tbBindUserActivities.setUaBuyUserId(order.getBuyerId());
            tbBindUserActivitiesMapper.insert(tbBindUserActivities);

            //额外的处理

        }

    }
}
