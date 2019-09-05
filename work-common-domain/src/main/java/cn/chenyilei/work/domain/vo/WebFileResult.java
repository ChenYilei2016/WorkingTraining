package cn.chenyilei.work.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @warn 仅用于 meditor的特殊返回形式
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/03/27 18:20
 */

@Data
@NoArgsConstructor
@ApiModel(value = "对于 mdeditor 的一种返回格式")
public class WebFileResult {
    private String url;
    private String message ;
    private Integer success;

    public static WebFileResult success (String url,String message){
        WebFileResult webFileResult = new WebFileResult();
        webFileResult.setSuccess(1);
        webFileResult.setMessage(message);
        webFileResult.setUrl(url);
        return webFileResult;
    }
    public static WebFileResult error (String message){
        WebFileResult webFileResult = new WebFileResult();
        webFileResult.setSuccess(0);
        webFileResult.setMessage(message);
        return webFileResult;
    }
}
