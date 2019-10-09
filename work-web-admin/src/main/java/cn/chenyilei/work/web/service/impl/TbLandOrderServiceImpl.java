package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.commonutils.MapUtils;
import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.TbOrderDto;
import cn.chenyilei.work.domain.mapper.*;
import cn.chenyilei.work.domain.pojo.internal_enum.OrderStatusEnum;
import cn.chenyilei.work.domain.pojo.land.*;
import cn.chenyilei.work.domain.pojo.land.ext.TbLandOrderExt;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.exception.OrderException;
import cn.chenyilei.work.web.service.TbLandOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 19:09
 */
@Service
public class TbLandOrderServiceImpl implements TbLandOrderService {

    public static final String ORDERBY_CREATETIME_DESC= "createtime desc";

    @Autowired
    TbLandOrderMapper tbLandOrderMapper;

    @Autowired
    TbLandCartMapper tbLandCartMapper;

    @Autowired
    TbBindUserLandMapper tbBindUserLandMapper;

    @Autowired
    TbLandMapper tbLandMapper;

    @Autowired
    TbLandOrderDetailMapper tbLandOrderDetailMapper;


    @Override
    @Transactional
    public Integer createOrder(TbOrderDto tbOrderDto) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();

        TbLandOrder tbLandOrder = new TbLandOrder();
        tbLandOrder.setCreatetime(new Date());
        tbLandOrder.setUpdatetime(new Date());
        tbLandOrder.setBuyerId(Integer.valueOf(user.getUserId()));
        tbLandOrder.setOrderstatus(OrderStatusEnum.NEW);

        //购物车的ID 集合
        List<Integer> cartIds = tbOrderDto.getCartIds();
        List<TbLandCart> tbLandCartList = tbLandCartMapper.selectByIdList(cartIds);
        List<Integer> tbLandIdList = tbLandCartList.stream().map(x->x.getLandId()).collect(Collectors.toList());
        List<TbLand> tbLandList = tbLandMapper.selectByIdList(tbLandIdList);

        if(tbLandCartList.isEmpty()){
            throw new OrderException(CodeResultEnum.REPEATE);
        }

        //TODO: 有商品被删除,暂时不做,购物车应该显示的时候就是灰色,进入这里是恶意操作
        if( cartIds.size() != tbLandList.size() ){
            for (TbLand tbLand : tbLandList) {
                if(! cartIds.contains( tbLand.getLandId()) ){
                    //商品已经不存在

                }
            }
        }

        //购物车ID 和 土地实例的映射
        Map<Integer, TbLand> cartIds_tbLands_MAP = MapUtils.toMap(cartIds, tbLandList);

        //订单价格
        Integer totalPrice= 0;

        for (TbLandCart tbLandCart : tbLandCartList) {
            //判断土地能否被购买等条件

            //查询田的相关信息
            TbLand tbLand = cartIds_tbLands_MAP.get(tbLandCart.getId());

            //改变田地被租属性,后续改变土地天数
            tbLand.setLandIsRent(true);
            // 如果不是1 说明这块田已经被卖,可以抛出异常
            int i = tbLandMapper.updateByPrimaryKeySelective(tbLand);
            if(i != 1){
                throw new OrderException(CodeResultEnum.REPEATE,tbLandCart.getName()+"土地已经被卖");
            }

            //计算总价
            totalPrice += tbLand.getLandPrice() * tbLandCart.getNumber();
        }

        tbLandOrder.setPrice(totalPrice);
        //插入订单,并获得订单号
        tbLandOrderMapper.insertSelective(tbLandOrder);

        //插入订单详情
        for (int i = 0 ; i < tbLandCartList.size() ; i++){
            TbLandCart tbLandCart = tbLandCartList.get(i);
            TbLand tbLand = cartIds_tbLands_MAP.get(tbLandCart.getId());

            TbLandOrderDetail tbLandOrderDetail = new TbLandOrderDetail();
            tbLandOrderDetail.setLandNumber(tbLandCart.getNumber());
            tbLandOrderDetail.setOrderId(tbLandOrder.getId());
            tbLandOrderDetail.setLandId(tbLand.getLandId());
            tbLandOrderDetail.setLandImage(tbLand.getLandImage());
            tbLandOrderDetail.setLandName(tbLand.getLandName());
            tbLandOrderDetail.setLandPrice(tbLand.getLandPrice());
            tbLandOrderDetailMapper.insert(tbLandOrderDetail);
        }

        //删除购物车的内容
        tbLandCartMapper.deleteByIdList(cartIds);
        return tbLandOrder.getId();
    }

    @Override
    @Transactional
    public void payOrder(TbOrderDto tbOrderDto) {
        Integer orderId = tbOrderDto.getOrderId();
        TbLandOrder tbLandOrder = tbLandOrderMapper.selectByPrimaryKey(orderId);

        if(tbLandOrder.getOrderstatus() == OrderStatusEnum.PAYSUCCESS){
            throw new OrderException(CodeResultEnum.REPEATE,"已经支付过!");
        }

        //支付
        //TODO: 测试环境直接支付成功!
        PayImpl(tbLandOrder);

        //更新订单状态
        tbLandOrderMapper.updateByPrimaryKeySelective(tbLandOrder);

        //回调
        //TODO: 测试环境直接成功
        notifyPay(tbLandOrder);
    }

    @Override
    public TbLandOrderExt queryOrderById(Integer orderId) {
        TbLandOrderExt tbLandOrderExts = tbLandOrderMapper.selectOrderExtById(orderId);
        return tbLandOrderExts;
    }

    @Override
    public List<TbLandOrder> selectMyOrders(PageRequest pageRequest) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        TbLandOrder tbLandOrder = new TbLandOrder();
        tbLandOrder.setBuyerId(Integer.valueOf(user.getUserId()));

        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize(),ORDERBY_CREATETIME_DESC);

        List<TbLandOrder> result = tbLandOrderMapper.select(tbLandOrder);
        return result;
    }


    private boolean PayImpl(TbLandOrder order){
        order.setOrderstatus(OrderStatusEnum.PAYING);
        return true;
    }

    private void notifyPay(TbLandOrder order){
        //判断成功失败等

        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        order.setOrderstatus(OrderStatusEnum.PAYSUCCESS);
        tbLandOrderMapper.updateByPrimaryKeySelective(order);

        //绑定商品
        TbLandOrderDetail tbLandOrderDetail = new TbLandOrderDetail();
        tbLandOrderDetail.setOrderId(order.getId());
        List<TbLandOrderDetail> detailList = tbLandOrderDetailMapper.select(tbLandOrderDetail);

        for (TbLandOrderDetail detail : detailList) {

            //绑定关系
            TbLand tbLand = tbLandMapper.selectByPrimaryKey(detail.getLandId());
            TbBindUserLand tbBindUserLand = new TbBindUserLand();
            tbBindUserLand.setUlBuyUserId(order.getBuyerId());
            tbBindUserLand.setUlSellUserId(tbLand.getLandUserId());
            tbBindUserLand.setUlLandId(detail.getLandId());
            tbBindUserLandMapper.insert(tbBindUserLand);


            //更新租的时间!
            Calendar calendar =new GregorianCalendar();
            tbLand.setLandUpdatetime(new Date());
            tbLand.setLandRentStarttime(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH,detail.getLandNumber());
            tbLand.setLandRentEndtime(calendar.getTime());
            tbLandMapper.updateByPrimaryKeySelective(tbLand);
        }
    }

}

