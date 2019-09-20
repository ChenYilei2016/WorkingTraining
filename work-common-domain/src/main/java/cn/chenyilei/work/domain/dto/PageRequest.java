package cn.chenyilei.work.domain.dto;

import lombok.Data;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/20 14:04
 */
@Data
public class PageRequest {
    private Integer page = 1;
    private Integer pageSize = 6;
}
