package cn.chenyilei.work.location;

import lombok.Data;
import org.springframework.web.client.RestTemplate;

/**
 *   根据IP 查找位置和相关信息
 *   {@link GaoDeLocation}
 *   {
 *       status: "1",  //值为0或1,0表示失败；1表示成功
 *       info: "OK",   //返回状态说明，status为0时，info返回错误原因，否则返回“OK”。
 *       infocode: "10000", //返回状态说明,10000代表正确,详情参阅info状态表
 *       province: "北京市", //若为直辖市则显示直辖市名称；如果在局域网 IP网段内，则返回“局域网”；非法IP以及国外IP则返回空
 *       city: "北京市", //若为直辖市则显示直辖市名称；如果为局域网网段内IP或者非法IP或国外IP，则返回空
 *       adcode: "110000",
 *       rectangle: "116.0119343,39.66127144;116.7829835,40.2164962" //所在城市范围的左下右上对标对
 *    }
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/09 11:08
 */
public class LocationUtil {
    //resttemplate
    static final RestTemplate restTemplate = new RestTemplate();
    //高德地图key
    private final static String GAODEKEY = "b5a7968fd3ca3a392ae21d9b7a585d8b";
    //IP 定位模版
    private final static String IP_LOCATION_TEMPLATE = "http://restapi.amap.com/v3/ip?key=%s&ip=%s";

    @Data
    static class GaoDeLocation{
        private String status;
        private String info;
        private String infocode;
        private String province;
        private String city;
        private String adcode;
        private String rectangle;
    }

    public static GaoDeLocation getLocationByIP(String ip){
        String format = String.format(IP_LOCATION_TEMPLATE, GAODEKEY, ip);
        return restTemplate.getForObject(format,GaoDeLocation.class);
    }

    public static void main(String[] args) {
        System.out.println(getLocationByIP("106.12.74.218"));

    }

}
