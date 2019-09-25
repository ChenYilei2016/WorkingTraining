package cn.chenyilei.work.domain.dto.wx;

import cn.chenyilei.work.domain.pojo.internal_enum.UserLevelEnum;
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
    private UserLevelEnum level ;
    private String address;
    private String phone;
    private String region;

}
