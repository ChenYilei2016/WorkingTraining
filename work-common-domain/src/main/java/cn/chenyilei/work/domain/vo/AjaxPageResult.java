package cn.chenyilei.work.domain.vo;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.dto.PageRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Ajax分页返回类
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/03/29 9:56
 */
@Data
@NoArgsConstructor
public class AjaxPageResult<T> extends AjaxResult<T> implements Serializable {
    private int page = 1;
    private int pageSize = 6;
    private long totalElements;

    public AjaxPageResult(boolean success, T data, String msg
                        ,int page,int pageSize, long totalElements) {
        super(success, data, msg);
        this.page = page;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }


    public static AjaxPageResult builder(){
        return new AjaxPageResult<>();
    }
    public AjaxPageResult<T> page(Integer page){
        this.page = page;
        return this;
    }
    public AjaxPageResult<T> pageSize(Integer pageSize){
        this.pageSize = pageSize;
        return this;
    }
    public AjaxPageResult<T> totalElements(long totalElements){
        this.totalElements = totalElements;
        return this;
    }
    public AjaxPageResult<T> success(boolean success){
        this.setSuccess(success);
        return this;
    }
    public AjaxPageResult<T> code(CodeResultEnum codeResultEnum){
        this.setCode(codeResultEnum.getCode());
        return this;
    }
    public AjaxPageResult<T> msg(String msg){
        this.setMsg(msg);
        return this;
    }
    public AjaxPageResult<T> data(T data){
        this.setData(data);
        return this;
    }
    public AjaxPageResult<T> pageRequest(PageRequest pageRequest){
        this.setPage(pageRequest.getPage());
        this.setPageSize(pageRequest.getPageSize());
        return this;
    }
}
