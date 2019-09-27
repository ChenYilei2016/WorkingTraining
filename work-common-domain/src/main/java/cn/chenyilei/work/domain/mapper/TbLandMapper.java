package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.land.TbLand;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface TbLandMapper extends Mapper<TbLand>, InsertListMapper<TbLand>,
        SelectByIdListMapper<TbLand,Integer> {
    List<TbLand> queryCustomerList(@Param("userId") String userId);
}