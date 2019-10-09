package cn.chenyilei.work.domain.pojo.activities.ext;

import cn.chenyilei.work.domain.pojo.activities.TbActivitiesOrder;
import cn.chenyilei.work.domain.pojo.activities.TbActivitiesOrderDetail;
import lombok.Data;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/30 11:00
 */
@Data
public class TbActivitiesOrderExt extends TbActivitiesOrder {
    List<TbActivitiesOrderDetail> tbActivitiesOrderDetails;
}
