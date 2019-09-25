package cn.chenyilei.work.domain.dto;

import com.github.pagehelper.IPage;
import lombok.Data;
import lombok.Setter;

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
    private Integer pageSize  = 6;

    public static PageRequest of(Integer page, Integer pageSize){
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(page);
        pageRequest.setPageSize(pageSize);
        return pageRequest;
    }

}
