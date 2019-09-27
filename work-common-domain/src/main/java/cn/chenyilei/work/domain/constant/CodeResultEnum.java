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
    SUCCESS(200,"成功"),
    BAD_REQUEST(400,"错误的请求"),
    UNAUTHORIZED(401,"没有认证"),
    FORBIDDEN(403,"权限不够"),

    INVALID_PARAM(70000,"错误的参数"),
    REPEATE(88888,"重复的操作"),
    INVALID_DO(20000,"这次请求被禁止!"),
    UNKNOWERROR(99999,"未知错误")
    ;

    private int code ;
    private String desc;
    CodeResultEnum(int code, String desc ){
        this.code = code;
        this.desc = desc;
    }
}
