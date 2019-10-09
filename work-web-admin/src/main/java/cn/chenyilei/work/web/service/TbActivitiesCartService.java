package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.dto.ActivitiesCartRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.pojo.activities.TbActivitiesCart;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/29 11:03
 */
public interface TbActivitiesCartService {
    List<TbActivitiesCart> selectMyCart(ActivitiesCartRequestParam param, PageRequest pageRequest);

    Integer insertCartOne(ActivitiesCartRequestParam.AcInsertCartOne param);

    void deleteCart(Integer activityId);

    void update(ActivitiesCartRequestParam.AcUpdateOne param);
}
