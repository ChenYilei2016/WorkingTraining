package cn.chenyilei.work.domain.dto;

import cn.chenyilei.work.domain.pojo.land.TbLand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 14:18
 */
@Data
public class LandRequestParam extends TbLand{

    private String startTime;
}
