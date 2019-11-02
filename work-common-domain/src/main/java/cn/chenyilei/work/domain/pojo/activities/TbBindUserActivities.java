package cn.chenyilei.work.domain.pojo.activities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "tb_bind_user_activities")
@Data
public class TbBindUserActivities {
    /**
     * 用户活动关系表
     */
    @Id
    @Column(name = "ua_id")
    private Integer uaId;

    /**
     * 客户ID
     */
    @Column(name = "ua_buy_user_id")
    private Integer uaBuyUserId;

    /**
     * 农户ID
     */
    @Column(name = "ua_sell_user_id")
    private Integer uaSellUserId;

    /**
     * 活动ID
     */
    @Column(name = "ua_activities_id")
    private Integer uaActivitiesId;

    /**
     * 买票数量
     */
    @Column(name = "ua_buy_number")
    private Integer uaBuyNumber;


}