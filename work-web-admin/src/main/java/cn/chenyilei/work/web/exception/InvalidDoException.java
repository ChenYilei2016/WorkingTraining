package cn.chenyilei.work.web.exception;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import lombok.Data;

/**
 * 不可用的操作
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 10:01
 */
@Data
public class InvalidDoException extends RuntimeException{
    private CodeResultEnum code;

    public InvalidDoException(CodeResultEnum code, String msg) {
        super(msg);
        this.code = code;
    }
}
