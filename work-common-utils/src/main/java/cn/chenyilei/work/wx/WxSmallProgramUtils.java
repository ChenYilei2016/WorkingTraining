package cn.chenyilei.work.wx;

import cn.chenyilei.work.commonutils.MapperUtils;
import cn.chenyilei.work.domain.constant.WxProperties;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 微信小程序助手
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 15:03
 */
public class WxSmallProgramUtils {
    static RestTemplate restTemplate = new RestTemplate();
    public static final String getOpenIdFromCode_TEMPLATE= "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    /**
     * 根据微信传来的code,获得用户的openid
     * @param code
     * @return
     */
    public static String getOpenIdFromCode(String code){
        String url = String.format(getOpenIdFromCode_TEMPLATE, WxProperties.AppId, WxProperties.AppSecret, code);
        String result = restTemplate.getForObject(url, String.class);
        Map<String, Object> resultMap = MapperUtils.json2map(result);
        if(resultMap.containsKey("openid")){
            return resultMap.get("openid").toString();
        }
        if(null == resultMap.get("errmsg")){
            throw new UnapprovedClientAuthenticationException("微信服务器连接失败!");
        }
        throw new UnapprovedClientAuthenticationException(resultMap.get("errmsg").toString());
    }

}
