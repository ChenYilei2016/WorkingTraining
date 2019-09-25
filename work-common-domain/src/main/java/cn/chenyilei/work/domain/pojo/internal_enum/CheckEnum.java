package cn.chenyilei.work.domain.pojo.internal_enum;

import lombok.Getter;

/**
 * 审核状态: 0 未审核, 1 审核成功 ,2 审核失败
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/21 11:40
 */
@Getter
public enum CheckEnum {
    NONE(0,"未审核") , SUCCESS(1,"审核成功"), FAIL(2,"审核失败")
    ;
    /**
     * 对应tb_role表中的 id
     */
    private int code ;
    private String desc ;

    CheckEnum(int code,String desc) {
        this.code = code;
        this.desc = desc;
    }
}
