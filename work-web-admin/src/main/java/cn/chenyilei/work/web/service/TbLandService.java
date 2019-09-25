package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.dto.LandRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.pojo.land.TbLand;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 14:09
 */
public interface TbLandService {
    List<TbLand> selectAll(LandRequestParam landRequestParam, PageRequest pageRequest);

    List<TbLand> selectMyList(LandRequestParam landRequestParam, PageRequest pageRequest);

    TbLand selectOne(Integer landId, LandRequestParam landRequestParam, PageRequest pageRequest);

    void registerLand(TbLand tbLand);

    void setOnline(boolean isopen);

    boolean getOnline();

    void updateLand(TbLand tbLand);

    void deleteLand(Integer landId);
}
