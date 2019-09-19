package cn.chenyilei.work.domain.pojo;

import javax.persistence.*;

@Table(name = "tb_role_permission")
public class TbRolePermission {
    @Id
    @Column(name = "role_permission_id")
    private Integer rolePermissionId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "permission_id")
    private Integer permissionId;

    /**
     * @return role_permission_id
     */
    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    /**
     * @param rolePermissionId
     */
    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return permission_id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}