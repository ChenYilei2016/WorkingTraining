package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.activities.TbActivitiesCart;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface TbActivitiesCartMapper
        extends Mapper<TbActivitiesCart>, SelectByIdListMapper<TbActivitiesCart,Integer>
, DeleteByIdListMapper<TbActivitiesCart,Integer> {
}