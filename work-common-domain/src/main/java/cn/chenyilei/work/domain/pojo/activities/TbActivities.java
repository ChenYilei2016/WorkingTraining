package cn.chenyilei.work.domain.pojo.activities;

import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_activities")
@Data
public class TbActivities {
    /**
     * 活动ID
     */
    @Id
    @Column(name = "activities_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activitiesId;

    /**
     * 哪个用户发布的活动
     */
    @Column(name = "activities_user_id")
    private Integer activitiesUserId;

    /**
     * 活动名称
     */
    @Column(name = "activities_name")
    private String activitiesName;

    /**
     * 活动地址
     */
    @Column(name = "activities_location")
    private String activitiesLocation;

    /**
     * 审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     */
    @Column(name = "activities_status")
    private CheckEnum activitiesStatus = CheckEnum.SUCCESS;

    /**
     * 活动价格
     */
    @Column(name = "activities_price")
    private Integer activitiesPrice;


    @Column(name = "activities_is_open")
    private boolean activitiesIsOpen;

    /**
     * 库存数量
     */
    @Column(name = "activities_storenumber")
    private Integer activitiesStorenumber;

    /**
     * 活动图片
     */
    @Column(name = "activities_image")
    private String activitiesImage;

    /**
     * 举办时间
     */
    @Column(name = "activities_starttime")
    private Date activitiesStarttime;

    /**
     * 结束时间
     */
    @Column(name = "activities_endtime")
    private Date activitiesEndtime;

    /**
     * 创建时间
     */
    @Column(name = "activities_createtime")
    private Date activitiesCreatetime;

    /**
     * 更新时间
     */
    @Column(name = "activities_updatetime")
    private Date activitiesUpdatetime;


}