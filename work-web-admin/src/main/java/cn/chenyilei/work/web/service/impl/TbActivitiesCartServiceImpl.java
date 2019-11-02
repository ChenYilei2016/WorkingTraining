package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.dto.ActivitiesCartRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.mapper.TbActivitiesCartMapper;
import cn.chenyilei.work.domain.mapper.TbActivitiesMapper;
import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.activities.TbActivitiesCart;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.pojo.land.TbLandCart;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.service.TbActivitiesCartService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/29 11:04
 */
@Service
public class TbActivitiesCartServiceImpl implements TbActivitiesCartService {

    @Autowired
    TbActivitiesMapper tbActivitiesMapper;

    @Autowired
    TbActivitiesCartMapper tbActivitiesCartMapper ;


    @Override
    public List<TbActivitiesCart> selectMyCart(ActivitiesCartRequestParam param, PageRequest pageRequest) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        TbActivitiesCart tbActivitiesCart = new TbActivitiesCart();
        BeanUtils.copyProperties(param,tbActivitiesCart);
        tbActivitiesCart.setUserId(Integer.valueOf(user.getUserId()));
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize());
        return tbActivitiesCartMapper.selectAll();
    }

    @Override
    public Integer insertCartOne(ActivitiesCartRequestParam.AcInsertCartOne param) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        Integer userId = Integer.valueOf(user.getUserId());
        TbActivitiesCart query = new TbActivitiesCart();
        query.setUserId(userId);
        query.setActivitiesId(param.getActivityId());

        TbActivitiesCart result = tbActivitiesCartMapper.selectOne(query);
        if(null == result){
            TbActivities tbActivities = tbActivitiesMapper.selectByPrimaryKey(param.getActivityId());

            //将土地中的信息放入购物车中便于查询
            result = new TbActivitiesCart();
            result.setImage(tbActivities.getActivitiesImage());
            result.setPrice(tbActivities.getActivitiesPrice());
            result.setName(tbActivities.getActivitiesName());
            result.setNumber(param.getNumber());
            result.setActivitiesId(param.getActivityId());
            result.setUserId(userId);
            tbActivitiesCartMapper.insertSelective(result);
        }else{
            //追加购物车数量
            result.setNumber(param.getNumber()+result.getNumber());
            tbActivitiesCartMapper.updateByPrimaryKey(result);
        }
        return result.getId();
    }

    @Override
    public void deleteCart(Integer activityId) {
        tbActivitiesCartMapper.deleteByPrimaryKey(activityId);
    }

    @Override
    public void update(ActivitiesCartRequestParam.AcUpdateOne param) {
        if(param.getNumber() == 0){
            tbActivitiesCartMapper.deleteByPrimaryKey(param.getActivityCartId());
            return ;
        }
        TbActivitiesCart tbActivitiesCart = tbActivitiesCartMapper.selectByPrimaryKey(param.getActivityCartId());
        tbActivitiesCart.setNumber(param.getNumber());
        tbActivitiesCartMapper.updateByPrimaryKeySelective(tbActivitiesCart);
    }
}
