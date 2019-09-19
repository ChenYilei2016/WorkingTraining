package cn.chenyilei.work.domain.pojo;

import javax.persistence.*;

@Table(name = "tb_user")
public class TbUser {
    /**
     * 用户唯一Id
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    /**
     * 用户账户名,如果是第三方,就是第三方id
     */
    private String username;

    /**
     * 用户的密码,如果是第三方登陆就无密码
     */
    private String password;

    /**
     * 微信唯一openid
     */
    private String wxopenid;

    /**
     * 用户类型,0代表无注册,2代表客户,3代表农户,1代表管理员
     */
    private Integer level;

    /**
     * 获取用户唯一Id
     *
     * @return user_id - 用户唯一Id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户唯一Id
     *
     * @param userId 用户唯一Id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户账户名,如果是第三方,就是第三方id
     *
     * @return username - 用户账户名,如果是第三方,就是第三方id
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户账户名,如果是第三方,就是第三方id
     *
     * @param username 用户账户名,如果是第三方,就是第三方id
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取用户的密码,如果是第三方登陆就无密码
     *
     * @return password - 用户的密码,如果是第三方登陆就无密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户的密码,如果是第三方登陆就无密码
     *
     * @param password 用户的密码,如果是第三方登陆就无密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取微信唯一openid
     *
     * @return wxopenid - 微信唯一openid
     */
    public String getWxopenid() {
        return wxopenid;
    }

    /**
     * 设置微信唯一openid
     *
     * @param wxopenid 微信唯一openid
     */
    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    /**
     * 获取用户类型, 1代表客户,2代表农户.
     *
     * @return level - 用户类型, 1代表客户,2代表农户.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置用户类型, 1代表客户,2代表农户.
     *
     * @param level 用户类型, 1代表客户,2代表农户.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}