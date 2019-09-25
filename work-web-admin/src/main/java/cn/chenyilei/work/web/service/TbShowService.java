package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.pojo.TbShow;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/20 16:26
 */
public interface TbShowService {

    List<TbShow> selectAll(PageRequest pageRequest);
    List<TbShow> selectMyList(PageRequest pageRequest);

    void insertShow(TbShow tbShow);

    void updateOne(TbShow tbShow);

    void deleteOne(Integer id);

    TbShow selectOneById(Integer id);
}
