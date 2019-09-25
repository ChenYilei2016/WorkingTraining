package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.land.TbLand;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface TbLandMapper extends Mapper<TbLand>, InsertListMapper<TbLand> {
}