package cn.chenyilei.work.domain.pojo.user;

import javax.persistence.*;

@Table(name = "tb_permission")
public class TbPermission {
    /**
     * 安全权限id
     */
    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    /**
     * 权限eng名称
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 权限名称
     */
    @Column(name = "permission_nickname")
    private String permissionNickname;

    /**
     * 允许通过的url
     */
    @Column(name = "permission_url")
    private String permissionUrl;

    /**
     * 获取安全权限id
     *
     * @return permission_id - 安全权限id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * 设置安全权限id
     *
     * @param permissionId 安全权限id
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取权限eng名称
     *
     * @return permission_name - 权限eng名称
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 设置权限eng名称
     *
     * @param permissionName 权限eng名称
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * 获取权限名称
     *
     * @return permission_nickname - 权限名称
     */
    public String getPermissionNickname() {
        return permissionNickname;
    }

    /**
     * 设置权限名称
     *
     * @param permissionNickname 权限名称
     */
    public void setPermissionNickname(String permissionNickname) {
        this.permissionNickname = permissionNickname;
    }

    /**
     * 获取允许通过的url
     *
     * @return permission_url - 允许通过的url
     */
    public String getPermissionUrl() {
        return permissionUrl;
    }

    /**
     * 设置允许通过的url
     *
     * @param permissionUrl 允许通过的url
     */
    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }
}