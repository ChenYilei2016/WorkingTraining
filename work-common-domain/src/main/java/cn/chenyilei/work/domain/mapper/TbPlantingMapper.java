package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.land.TbPlanting;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbPlantingMapper extends Mapper<TbPlanting> {

    List<TbPlanting> selectAllWithCustomer(String userId);
    List<TbPlanting> selectAllWithFarmer(String userId);
}