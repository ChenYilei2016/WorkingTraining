package cn.chenyilei.work.web.exception;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import lombok.Data;

/**
 * 订单不可用的操作
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 10:01
 */
@Data
public class OrderException extends RuntimeException{
    private CodeResultEnum code;

    public OrderException(CodeResultEnum code, String msg) {
        super(msg);
        this.code = code;
    }
    public OrderException(CodeResultEnum code) {
        super(code.getDesc());
        this.code = code;
    }
}

