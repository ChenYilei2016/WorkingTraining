package cn.chenyilei.work.domain.pojo.activities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "tb_activities_order_detail")
@Data
public class TbActivitiesOrderDetail {
    /**
     * 活动订单详情主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 订单的ID
     */
    @Column(name = "order_id")
    private Integer orderId;
    /**
     * 活动ID
     */
    @Column(name = "activities_id")
    private Integer activitiesId;

    /**
     * 活动名
     */
    @Column(name = "activities_name")
    private String activitiesName;

    /**
     * 买的数量
     */
    @Column(name = "number")
    private Integer number;

    @Column(name = "activities_image")
    private String activitiesImage;


    @Column(name = "activities_price")
    private Integer activitiesPrice;

}