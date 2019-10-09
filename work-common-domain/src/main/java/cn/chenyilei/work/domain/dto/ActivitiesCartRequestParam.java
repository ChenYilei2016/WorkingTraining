package cn.chenyilei.work.domain.dto;

import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.land.TbLandCart;
import lombok.Data;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 10:40
 */
@Data
public class ActivitiesCartRequestParam extends TbActivities {

    @Data
    public static class AcInsertCartOne {
        private Integer number;
        private Integer activityId;
    }

    @Data
    public static class AcUpdateOne {
        private Integer activityCartId;
        private Integer number;
    }
}
