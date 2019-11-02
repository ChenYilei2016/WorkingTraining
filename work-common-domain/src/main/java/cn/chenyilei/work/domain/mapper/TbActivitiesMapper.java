package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.activities.ext.SellallVo;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbActivitiesMapper extends Mapper<TbActivities>, SelectByIdListMapper<TbActivities,Integer> {
    Boolean hasStore(@Param("activityId") Integer activityId);
    Integer lessNumber(@Param("activityId") Integer activityId,@Param("number") Integer number);
    List<TbActivities> queryCustomerList(@Param("userId") Integer userId);
    List<SellallVo> querySellallList(@Param("userId") Integer userId,@Param("createtime") String createtime);

    // TODO: WARM: 临时低效率
    @Select("SELECT * FROM tb_activities ORDER BY RAND() LIMIT 1")
    TbActivities randOne();
}