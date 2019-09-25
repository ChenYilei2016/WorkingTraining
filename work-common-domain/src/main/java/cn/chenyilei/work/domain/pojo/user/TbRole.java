package cn.chenyilei.work.domain.pojo.user;

import javax.persistence.*;

@Table(name = "tb_role")
public class TbRole {
    /**
     * 安全角色唯一id
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 角色eng名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色名称
     */
    @Column(name = "role_nickname")
    private String roleNickname;

    /**
     * 获取安全角色唯一id
     *
     * @return role_id - 安全角色唯一id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置安全角色唯一id
     *
     * @param roleId 安全角色唯一id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色eng名称
     *
     * @return role_name - 角色eng名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色eng名称
     *
     * @param roleName 角色eng名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色名称
     *
     * @return role_nickname - 角色名称
     */
    public String getRoleNickname() {
        return roleNickname;
    }

    /**
     * 设置角色名称
     *
     * @param roleNickname 角色名称
     */
    public void setRoleNickname(String roleNickname) {
        this.roleNickname = roleNickname;
    }
}