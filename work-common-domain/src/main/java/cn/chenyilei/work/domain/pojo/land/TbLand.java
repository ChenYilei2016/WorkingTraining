package cn.chenyilei.work.domain.pojo.land;

import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_land")
@Data
public class TbLand {
    /**
     * 土地ID
     */
    @Id
    @Column(name = "land_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer landId;

    /**
     * 土地归属用户
     */
    @Column(name = "land_user_id")
    private Integer landUserId;

    /**
     * 土地名称
     */
    @Column(name = "land_name")
    private String landName;

    /**
     * 审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     */
    @Column(name = "land_status")
    private CheckEnum landStatus = CheckEnum.SUCCESS;

    /**
     * 土地位置的经纬度( "116.0119343,39.66127144;116.7829835,40.2164962" )
     */
    @Column(name = "land_rectangle")
    private String landRectangle;

    /**
     * 土地有无被租
     */
    @Column(name = "land_is_rent")
    private Boolean landIsRent;

    @Column(name = "land_is_open")
    private Boolean landIsOpen;
    /**
     * 土地价格 (1天多少钱)
     */
    @Column(name = "land_price")
    private Integer landPrice;

    /**
     * 更新时间
     */
    @Column(name = "land_updatetime")
    private Date landUpdatetime;

    /**
     * 土地开始租的时间
     */
    @Column(name = "land_rent_starttime")
    private Date landRentStarttime;

    /**
     * 土地租到期时间
     */
    @Column(name = "land_rent_endtime")
    private Date landRentEndtime;

    /**
     * 创建时间
     */
    @Column(name = "land_createtime")
    private Date landCreatetime;

    /**
     * 土地图片
     */
    @Column(name = "land_image")
    private String landImage;

    /**
     * 土地介绍
     */
    @Column(name = "land_information")
    private Date landInformation;
}