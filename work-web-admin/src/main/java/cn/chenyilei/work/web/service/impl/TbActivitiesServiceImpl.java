package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.dto.ActivitiesQueryParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.mapper.TbActivitiesMapper;
import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.activities.ext.SellallVo;
import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.service.TbActivitiesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/27 14:38
 */
@Service
public class TbActivitiesServiceImpl implements TbActivitiesService {
    public static final String ORDERBY_CREATETIME_DESC= "activities_createtime desc";

    @Autowired
    TbActivitiesMapper tbActivitiesMapper;

    @Override
    public Integer registerOneActivitie(TbActivities tbActivities) {
        tbActivities.setActivitiesCreatetime(new Date());
        tbActivities.setActivitiesUpdatetime(new Date());
        tbActivities.setActivitiesStatus(CheckEnum.SUCCESS);
        tbActivities.setActivitiesIsOpen(true);

        //设置用户的ID
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        tbActivities.setActivitiesUserId(Integer.valueOf(user.getUserId()));
        tbActivitiesMapper.insertSelective(tbActivities);
        return tbActivities.getActivitiesId();
    }

    @Override
    public TbActivities selectOneById(Integer activitiesId, ActivitiesQueryParam param) {
        TbActivities tbActivities = new TbActivities();
        BeanUtils.copyProperties(param,tbActivities);
        tbActivities.setActivitiesId(activitiesId);
        return tbActivitiesMapper.selectOne(tbActivities);
    }

    @Override
    public List<TbActivities> selectFarmerList(ActivitiesQueryParam param, PageRequest pageRequest) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        TbActivities tbActivities = new TbActivities();
        BeanUtils.copyProperties(param,tbActivities);
        tbActivities.setActivitiesUserId(Integer.valueOf(user.getUserId()));
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize(),ORDERBY_CREATETIME_DESC );
        return tbActivitiesMapper.select(tbActivities);
    }

    @Override
    public void updateActivities(TbActivities tbActivities) {
        tbActivities.setActivitiesUpdatetime(new Date());
        tbActivitiesMapper.updateByPrimaryKeySelective(tbActivities);
    }

    @Override
    public void deleteActivities(Integer activitiesId) {
        //这里不考虑卖出了
        tbActivitiesMapper.deleteByPrimaryKey(activitiesId);
    }

    @Override
    public List<TbActivities> selectAll(ActivitiesQueryParam param, PageRequest pageRequest) {
        TbActivities tbActivities = new TbActivities();
        BeanUtils.copyProperties(param,tbActivities);
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize(),ORDERBY_CREATETIME_DESC);
        return tbActivitiesMapper.selectAll();
    }

    @Override
    public List<TbActivities> selectCustomerList(ActivitiesQueryParam param, PageRequest pageRequest) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        TbActivities tbActivities = new TbActivities();
        BeanUtils.copyProperties(param,tbActivities);
        tbActivities.setActivitiesUserId(Integer.valueOf(user.getUserId()));
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize(),ORDERBY_CREATETIME_DESC );
        return tbActivitiesMapper.select(tbActivities);
    }

    @Override
    public List<SellallVo> sellall(String startTime) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        List<SellallVo> sellallVos = tbActivitiesMapper.querySellallList(Integer.valueOf(user.getUserId()), startTime);
        return sellallVos;
    }

}
