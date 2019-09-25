package cn.chenyilei.work.domain.dto;

import cn.chenyilei.work.domain.pojo.land.TbPlanting;
import lombok.Data;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 15:23
 */
@Data
public class PlantingRequestParam extends TbPlanting {

    @Data
    public static class insertOne{
        private Integer plantingLandId;
        private String plantingInformation;
        private String plantingImage;
    }

}
