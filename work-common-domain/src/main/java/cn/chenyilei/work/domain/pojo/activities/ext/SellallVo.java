package cn.chenyilei.work.domain.pojo.activities.ext;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/10/11 15:07
 */
@Data
public class SellallVo {
    private Integer orderId;
    private String username;
    private String activitiesName;
    private Integer activitiesPrice;
    private Integer number;
    private String activitiesImage;
    private Date createtime;

}
