package cn.chenyilei.work.domain.pojo.activities;

import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_activities")
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
     * 获取哪个用户发布的活动
     *
     * @return activities_user_id - 哪个用户发布的活动
     */
    public Integer getActivitiesUserId() {
        return activitiesUserId;
    }

    /**
     * 设置哪个用户发布的活动
     *
     * @param activitiesUserId 哪个用户发布的活动
     */
    public void setActivitiesUserId(Integer activitiesUserId) {
        this.activitiesUserId = activitiesUserId;
    }

    /**
     * 获取活动名称
     *
     * @return activities_name - 活动名称
     */
    public String getActivitiesName() {
        return activitiesName;
    }

    /**
     * 设置活动名称
     *
     * @param activitiesName 活动名称
     */
    public void setActivitiesName(String activitiesName) {
        this.activitiesName = activitiesName;
    }

    /**
     * 获取活动地址
     *
     * @return activities_location - 活动地址
     */
    public String getActivitiesLocation() {
        return activitiesLocation;
    }

    /**
     * 设置活动地址
     *
     * @param activitiesLocation 活动地址
     */
    public void setActivitiesLocation(String activitiesLocation) {
        this.activitiesLocation = activitiesLocation;
    }

    /**
     * 获取审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     *
     * @return activities_status - 审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     */
    public CheckEnum getActivitiesStatus() {
        return activitiesStatus;
    }

    /**
     * 设置审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     *
     * @param activitiesStatus 审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     */
    public void setActivitiesStatus(CheckEnum activitiesStatus) {
        this.activitiesStatus = activitiesStatus;
    }

    /**
     * 获取活动价格
     *
     * @return activities_price - 活动价格
     */
    public Integer getActivitiesPrice() {
        return activitiesPrice;
    }

    /**
     * 设置活动价格
     *
     * @param activitiesPrice 活动价格
     */
    public void setActivitiesPrice(Integer activitiesPrice) {
        this.activitiesPrice = activitiesPrice;
    }

    /**
     * 获取活动图片
     *
     * @return activities_image - 活动图片
     */
    public String getActivitiesImage() {
        return activitiesImage;
    }

    /**
     * 设置活动图片
     *
     * @param activitiesImage 活动图片
     */
    public void setActivitiesImage(String activitiesImage) {
        this.activitiesImage = activitiesImage;
    }

    /**
     * 获取举办时间
     *
     * @return activities_starttime - 举办时间
     */
    public Date getActivitiesStarttime() {
        return activitiesStarttime;
    }

    /**
     * 设置举办时间
     *
     * @param activitiesStarttime 举办时间
     */
    public void setActivitiesStarttime(Date activitiesStarttime) {
        this.activitiesStarttime = activitiesStarttime;
    }

    public boolean isActivitiesIsOpen() {
        return activitiesIsOpen;
    }

    public void setActivitiesIsOpen(boolean activitiesIsOpen) {
        this.activitiesIsOpen = activitiesIsOpen;
    }

    /**
     * 获取结束时间
     *
     * @return activities_endtime - 结束时间
     */
    public Date getActivitiesEndtime() {
        return activitiesEndtime;
    }

    /**
     * 设置结束时间
     *
     * @param activitiesEndtime 结束时间
     */
    public void setActivitiesEndtime(Date activitiesEndtime) {
        this.activitiesEndtime = activitiesEndtime;
    }

    /**
     * 获取创建时间
     *
     * @return activities_createtime - 创建时间
     */
    public Date getActivitiesCreatetime() {
        return activitiesCreatetime;
    }

    /**
     * 设置创建时间
     *
     * @param activitiesCreatetime 创建时间
     */
    public void setActivitiesCreatetime(Date activitiesCreatetime) {
        this.activitiesCreatetime = activitiesCreatetime;
    }

    /**
     * 获取更新时间
     *
     * @return activities_updatetime - 更新时间
     */
    public Date getActivitiesUpdatetime() {
        return activitiesUpdatetime;
    }

    /**
     * 设置更新时间
     *
     * @param activitiesUpdatetime 更新时间
     */
    public void setActivitiesUpdatetime(Date activitiesUpdatetime) {
        this.activitiesUpdatetime = activitiesUpdatetime;
    }
}