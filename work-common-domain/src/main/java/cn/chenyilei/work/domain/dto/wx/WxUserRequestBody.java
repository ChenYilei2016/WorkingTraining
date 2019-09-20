package cn.chenyilei.work.domain.dto.wx;

import lombok.Data;

/**
 * User的接收类
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/19 14:10
 */
@Data
public class WxUserRequestBody {
    private String userId;
    private String username;
    private String password;
    private Integer level ;

}
