package cn.chenyilei.work.domain.vo;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ajax页面返回主体
 *
 * @author chenyilei
 * @date 2019/03/14- 22:13
 */
@Data
@NoArgsConstructor
public class AjaxResult<T> implements Serializable {

    private boolean success ;
    private T data;
    private String msg;
    private int code = 200;

    public static AjaxResult error(String msg){
        return new AjaxResult(false,null,msg);
    }
    public static AjaxResult error(String msg, CodeResultEnum codeResultEnum){
        AjaxResult ajaxResult = new AjaxResult(false, null, msg);
        ajaxResult.setCode(codeResultEnum.getCode());
        return ajaxResult;
    }
    public static AjaxResult success(Object data,String msg){
        return new AjaxResult(true,data,msg);
    }

    public AjaxResult(boolean success, T data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }
}
