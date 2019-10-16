package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.dto.ActivitiesQueryParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.activities.ext.SellallVo;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/27 14:38
 */
public interface TbActivitiesService {
    Integer registerOneActivitie(TbActivities tbActivities);

    TbActivities selectOneById(Integer activitiesId, ActivitiesQueryParam param);

    List<TbActivities> selectFarmerList(ActivitiesQueryParam param, PageRequest pageRequest);

    void updateActivities(TbActivities tbActivities);

    void deleteActivities(Integer activitiesId);

    List<TbActivities> selectAll(ActivitiesQueryParam param, PageRequest pageRequest);

    List<TbActivities> selectCustomerList(ActivitiesQueryParam param, PageRequest pageRequest);

    List<SellallVo> sellall(String startTime);
}
