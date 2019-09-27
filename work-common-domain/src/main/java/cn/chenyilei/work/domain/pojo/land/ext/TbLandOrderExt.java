package cn.chenyilei.work.domain.pojo.land.ext;

import cn.chenyilei.work.domain.pojo.land.TbLandOrder;
import cn.chenyilei.work.domain.pojo.land.TbLandOrderDetail;
import lombok.Data;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 19:35
 */
@Data
public class TbLandOrderExt extends TbLandOrder {

    private List<TbLandOrderDetail> tbLandOrderDetails;
}
