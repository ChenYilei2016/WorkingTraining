package cn.chenyilei.work.domain.constant;

import lombok.Getter;
import lombok.ToString;

/**
 * 返回值code 枚举
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/16 10:24
 */
@Getter
public enum CodeResultEnum {
    SUCCESS(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401), // 没有认证
    FORBIDDEN(403), // 权限不够

    ;

    private int code ;
    CodeResultEnum(int code){
        this.code = code;
    }
}
