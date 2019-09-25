package cn.chenyilei.work.domain.pojo.user;

import cn.chenyilei.work.domain.pojo.internal_enum.UserLevelEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Table(name = "tb_user")
@Data
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
    @Column(name = "username")
    private String username;

    /**
     * 用户的密码,如果是第三方登陆就无密码
     */
    @Column(name = "password")
    @JsonIgnore
    transient private String password;

    /**
     * 微信唯一openid
     */
    @Column(name = "wxopenid")
    private String wxopenid;

    /**
     * 用户类型,0代表无注册,1代表管理员,2代表客户,3代表农户
     */
    @Column(name = "level")
    private UserLevelEnum level;

    /**
     * 电话
     */
    @Column(name = "phone" )
    private String phone;

    /**
     * 主要地址
     */
    @Column(name = "address" )
    private String address;

    /**
     * 省市区
     */
    @Column(name = "region" )
    private String region;

}