package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.activities.ext.SellallVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbActivitiesMapper extends Mapper<TbActivities>, SelectByIdListMapper<TbActivities,Integer> {
    Boolean hasStore(@Param("activityId") Integer activityId);
    Integer lessNumber(@Param("activityId") Integer activityId,@Param("number") Integer number);
    List<TbActivities> queryCustomerList(@Param("userId") Integer userId);
    List<SellallVo> querySellallList(@Param("userId") Integer userId,@Param("createtime") String createtime);
}