package cn.chenyilei.work.domain.pojo.internal_enum;

import lombok.Getter;

/**
 * 用户类型,0代表无注册,2代表客户,3代表农户,1代表管理员
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/21 10:40
 */
@Getter
public enum UserLevelEnum {
    NONE(0,"没有权限"),ADMIN(1,"管理员"),CUSTOMER(2,"客户"),FARMER(3,"农户");

    /**
     * 对应tb_role表中的 id
     */
    private int roleId ;
    private String desc ;

    UserLevelEnum(int roleId, String desc) {
        this.roleId = roleId;
        this.desc = desc;
    }
    public boolean is(String str){
        return this.name().equals(str);
    }
}
