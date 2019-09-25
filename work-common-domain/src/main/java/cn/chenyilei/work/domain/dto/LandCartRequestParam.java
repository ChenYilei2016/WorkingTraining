package cn.chenyilei.work.domain.dto;

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
public class LandCartRequestParam extends TbLandCart {

    @Data
    public static class InsertCartOne{
        private Integer number;
        private Integer landId;
    }

    @Data
    public static class UpdateOne{
        private Integer landCartId;
        private Integer number;
    }
}
