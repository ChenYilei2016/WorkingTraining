package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.dto.LandCartRequestParam;
import cn.chenyilei.work.domain.mapper.TbLandCartMapper;
import cn.chenyilei.work.domain.mapper.TbLandMapper;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.pojo.land.TbLandCart;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.service.TblandCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 10:44
 */
@Service
public class TblandCartServiceImpl implements TblandCartService {
    @Autowired
    TbLandCartMapper tbLandCartMapper;
    @Autowired
    TbLandMapper tbLandMapper;

    @Override
    public List<TbLandCart> selectMyCart(LandCartRequestParam landCartRequestParam) {
        TbLandCart tbLandCart = new TbLandCart();
        BeanUtils.copyProperties(landCartRequestParam,tbLandCart);

        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        tbLandCart.setUserId(Integer.valueOf(user.getUserId()));
        List<TbLandCart> result = tbLandCartMapper.select(tbLandCart);
        return result;
    }

    @Override
    public Integer insertCartOne(LandCartRequestParam.InsertCartOne param) {
        TbLand tbLand = tbLandMapper.selectByPrimaryKey(param.getLandId());

        //将土地中的信息放入购物车中便于查询
        TbLandCart tbLandCart = new TbLandCart();
        tbLandCart.setImage(tbLand.getLandImage());
        tbLandCart.setPrice(tbLand.getLandPrice());
        tbLandCart.setName(tbLand.getLandName());

        tbLandCart.setId(param.getLandId());
        tbLandCart.setNumber(param.getNumber());

        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        tbLandCart.setUserId(Integer.valueOf(user.getUserId()));

        tbLandCartMapper.insertSelective(tbLandCart);
        return tbLandCart.getId();
    }

    @Override
    public void deleteCart(Integer cartId) {
        tbLandCartMapper.deleteByPrimaryKey(cartId);
    }

    @Override
    public void update(LandCartRequestParam.UpdateOne param) {
        TbLandCart tbLandCart = new TbLandCart();
        tbLandCart.setNumber(param.getNumber());
        tbLandCart.setId(param.getLandCartId());
        tbLandCartMapper.updateByPrimaryKeySelective(tbLandCart);
    }
}
