package cn.chenyilei.work.web.service;

import cn.chenyilei.work.domain.dto.LandCartRequestParam;
import cn.chenyilei.work.domain.pojo.land.TbLandCart;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 10:44
 */
public interface TblandCartService {
    List<TbLandCart> selectMyCart(LandCartRequestParam landCartRequestParam);

    Integer insertCartOne(LandCartRequestParam.InsertCartOne landCartRequestParam);

    void deleteCart(Integer cartId);

    void update(LandCartRequestParam.UpdateOne landCartRequestParam);
}
