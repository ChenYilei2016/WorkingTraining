package cn.chenyilei.work.domain.pojo.activities;

import javax.persistence.*;

@Table(name = "tb_activities_order_detail")
public class TbActivitiesOrderDetail {
    /**
     * 活动订单详情主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private Integer number;

    /**
     * 获取活动订单详情主键
     *
     * @return id - 活动订单详情主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置活动订单详情主键
     *
     * @param id 活动订单详情主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取活动ID
     *
     * @return activities_id - 活动ID
     */
    public Integer getActivitiesId() {
        return activitiesId;
    }

    /**
     * 设置活动ID
     *
     * @param activitiesId 活动ID
     */
    public void setActivitiesId(Integer activitiesId) {
        this.activitiesId = activitiesId;
    }

    /**
     * 获取活动名
     *
     * @return activities_name - 活动名
     */
    public String getActivitiesName() {
        return activitiesName;
    }

    /**
     * 设置活动名
     *
     * @param activitiesName 活动名
     */
    public void setActivitiesName(String activitiesName) {
        this.activitiesName = activitiesName;
    }

    /**
     * 获取买的数量
     *
     * @return number - 买的数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置买的数量
     *
     * @param number 买的数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}