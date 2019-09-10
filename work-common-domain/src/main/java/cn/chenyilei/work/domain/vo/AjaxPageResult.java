package cn.chenyilei.work.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/03/29 9:56
 */
@Data
@NoArgsConstructor
public class AjaxPageResult extends AjaxResult implements Serializable {
    private int page;
    private int pageSize;
    private long totalElements;

    public AjaxPageResult(boolean success, Object data, String msg
                        ,int page,int pageSize, long totalElements) {
        super(success, data, msg);
        this.page = page;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }
}
