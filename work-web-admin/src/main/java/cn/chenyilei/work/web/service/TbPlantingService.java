package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.PlantingRequestParam;
import cn.chenyilei.work.domain.pojo.land.TbPlanting;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 14:28
 */
public interface TbPlantingService {
    List<TbPlanting> selectAll(PageRequest pageRequest, Boolean isCustomer);

    void insertOne(PlantingRequestParam.insertOne param);

    void deleteOne(Integer plantingId);

    List<TbPlanting> selectOneByLandId(Integer landId);
}
