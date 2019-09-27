package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.land.TbLandOrder;
import cn.chenyilei.work.domain.pojo.land.ext.TbLandOrderExt;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbLandOrderMapper extends Mapper<TbLandOrder> {
    TbLandOrderExt selectOrderExtById(@Param("orderId") Integer orderId);
}