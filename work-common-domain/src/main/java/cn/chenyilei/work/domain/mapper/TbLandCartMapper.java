package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.land.TbLandCart;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface TbLandCartMapper extends Mapper<TbLandCart>,
        SelectByIdListMapper<TbLandCart,Integer> , DeleteByIdListMapper<TbLandCart,Integer> {
}