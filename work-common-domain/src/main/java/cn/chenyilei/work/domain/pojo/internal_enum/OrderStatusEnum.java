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

    PAYSUCCESS(1,"支付成功"),

    SUCCESS(200,"完成订单")

    ;

    private int roleId ;
    private String desc ;

    OrderStatusEnum(int roleId, String desc) {
        this.roleId = roleId;
        this.desc = desc;
    }
}
