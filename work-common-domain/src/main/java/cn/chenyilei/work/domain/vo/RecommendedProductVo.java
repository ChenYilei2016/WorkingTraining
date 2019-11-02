package cn.chenyilei.work.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/10/22 13:59
 */
@Data
public class RecommendedProductVo{
    private Integer id;
    @ApiModelProperty(name = "type",value = "land是田,activities是活动")
    private String type;
    private String image;
    private Integer price;
    private String information;
}