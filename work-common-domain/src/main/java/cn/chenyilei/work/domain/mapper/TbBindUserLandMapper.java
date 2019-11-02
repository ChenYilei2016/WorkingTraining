package cn.chenyilei.work.domain.mapper;

import cn.chenyilei.work.domain.pojo.land.TbBindUserLand;
import cn.chenyilei.work.domain.pojo.land.ext.TbBindUserLandVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbBindUserLandMapper extends Mapper<TbBindUserLand> {

    List<TbBindUserLandVo> selectVoAll();
}