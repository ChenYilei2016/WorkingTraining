package cn.chenyilei.work.domain.constant;

import lombok.Getter;
import lombok.ToString;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/16 10:24
 */
@Getter
public enum CodeResultEnum {
    SUCCESS(200),
    UNAUTHORIZED(401), // 没有认证
    FORBIDDEN(403), // 权限不够

    ;

    private int code ;
    CodeResultEnum(int code){
        this.code = code;
    }
}
