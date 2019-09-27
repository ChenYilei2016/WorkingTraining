package cn.chenyilei.work.domain.dto;

import cn.chenyilei.work.domain.pojo.land.TbLandOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/26 10:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbOrderDto {
    private Integer orderId;

    private List<Integer> cartIds;
    private Integer produceId;
}
