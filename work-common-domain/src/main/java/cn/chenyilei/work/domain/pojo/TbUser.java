package cn.chenyilei.work.domain.pojo;

import cn.chenyilei.work.domain.security.AuthenticationUser;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * {@link #id} 自己服务器的唯一ID
 * 对于微信注册的,username应和wxopenid 一样
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 14:53
 */
@Table(name = "tb_user")
@Data
public class TbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String username;
    private String password;
    private String authorities;
    private String wxopenid;


}
