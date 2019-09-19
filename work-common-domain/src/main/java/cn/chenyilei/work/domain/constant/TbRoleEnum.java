package cn.chenyilei.work.domain.constant;

import cn.chenyilei.work.domain.pojo.TbRole;
import lombok.Getter;

/**
 * 数据库角色枚举
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/18 16:09
 */
@Getter
public enum TbRoleEnum {
    ROLE_ADMIN(1,"管理员"),
    ROLE_CUSTOMER(2,"客户"),
    ROLE_FARMER(3,"农户");

    public static TbRoleEnum getRole(int level){
        for (TbRoleEnum tbRoleEnum : TbRoleEnum.values()) {
            if(level == tbRoleEnum.getLevel()){
                return tbRoleEnum;
            }
        }
        throw new RuntimeException("错误的level参数!");
    }

    private int level = 0;
    private String roleName;

    TbRoleEnum(int level,String roleName) {
        this.level = level;
        this.roleName = roleName;
    }
}
