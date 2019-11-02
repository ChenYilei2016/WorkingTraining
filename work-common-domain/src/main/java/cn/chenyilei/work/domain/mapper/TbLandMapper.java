package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.land.TbLand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface TbLandMapper extends Mapper<TbLand>, InsertListMapper<TbLand>,
        SelectByIdListMapper<TbLand,Integer> {
    List<TbLand> queryCustomerList(@Param("userId") String userId);

    // TODO: WARM: 临时低效率
    @Select("SELECT * FROM tb_land where land_is_open = 1 and land_is_rent = 0 ORDER BY RAND() LIMIT 1")
    TbLand randOne();
}