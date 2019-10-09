package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.dto.ActivitiesCartRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.mapper.TbActivitiesCartMapper;
import cn.chenyilei.work.domain.mapper.TbActivitiesMapper;
import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.activities.TbActivitiesCart;
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
        TbActivities tbActivities = tbActivitiesMapper.selectByPrimaryKey(param.getActivityId());

        TbActivitiesCart tbActivitiesCart = new TbActivitiesCart();
        tbActivitiesCart.setNumber(param.getNumber());
        tbActivitiesCart.setPrice(tbActivities.getActivitiesPrice());
        tbActivitiesCart.setImage(tbActivities.getActivitiesImage());
        tbActivitiesCart.setName(tbActivities.getActivitiesName());
        tbActivitiesCart.setUserId(Integer.valueOf(user.getUserId()));
        tbActivitiesCart.setActivitiesId(param.getActivityId());

        tbActivitiesCartMapper.insert(tbActivitiesCart);
        return tbActivitiesCart.getId();
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
