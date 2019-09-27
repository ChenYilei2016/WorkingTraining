package cn.chenyilei.work.domain.pojo.internal_enum;

import lombok.Getter;

/**
 * 订单状态
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 10:52
 */
@Getter
public enum  OrderStatusEnum {

    NEW(0,"创建新订单"),
    PAYING(1,"支付中"),
    PAYSUCCESS(2,"支付成功"),

    FAIL(3,"支付失败"),

    SUCCESS(200,"完成订单")

    ;

    private int code ;
    private String desc ;

    OrderStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
