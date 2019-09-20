package cn.chenyilei.work.domain.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_land")
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
     * 审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     */
    @Column(name = "land_status")
    private Integer landStatus;

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

    /**
     * 土地价格 (1天多少钱)
     */
    @Column(name = "land_price")
    private BigDecimal landPrice;

    /**
     * 创建时间
     */
    @Column(name = "land_createtime")
    private Date landCreatetime;

    /**
     * 更新时间
     */
    @Column(name = "land_updatetime")
    private Date landUpdatetime;

    /**
     * 获取土地ID
     *
     * @return land_id - 土地ID
     */
    public Integer getLandId() {
        return landId;
    }

    /**
     * 设置土地ID
     *
     * @param landId 土地ID
     */
    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    /**
     * 获取土地归属用户
     *
     * @return land_user_id - 土地归属用户
     */
    public Integer getLandUserId() {
        return landUserId;
    }

    /**
     * 设置土地归属用户
     *
     * @param landUserId 土地归属用户
     */
    public void setLandUserId(Integer landUserId) {
        this.landUserId = landUserId;
    }

    /**
     * 获取审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     *
     * @return land_status - 审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     */
    public Integer getLandStatus() {
        return landStatus;
    }

    /**
     * 设置审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     *
     * @param landStatus 审核状态: 0 未审核, 1 审核成功 ,2 审核失败
     */
    public void setLandStatus(Integer landStatus) {
        this.landStatus = landStatus;
    }

    /**
     * 获取土地位置的经纬度( "116.0119343,39.66127144;116.7829835,40.2164962" )
     *
     * @return land_rectangle - 土地位置的经纬度( "116.0119343,39.66127144;116.7829835,40.2164962" )
     */
    public String getLandRectangle() {
        return landRectangle;
    }

    /**
     * 设置土地位置的经纬度( "116.0119343,39.66127144;116.7829835,40.2164962" )
     *
     * @param landRectangle 土地位置的经纬度( "116.0119343,39.66127144;116.7829835,40.2164962" )
     */
    public void setLandRectangle(String landRectangle) {
        this.landRectangle = landRectangle;
    }

    /**
     * 获取土地有无被租
     *
     * @return land_is_rent - 土地有无被租
     */
    public Boolean getLandIsRent() {
        return landIsRent;
    }

    /**
     * 设置土地有无被租
     *
     * @param landIsRent 土地有无被租
     */
    public void setLandIsRent(Boolean landIsRent) {
        this.landIsRent = landIsRent;
    }

    /**
     * 获取土地价格 (1天多少钱)
     *
     * @return land_price - 土地价格 (1天多少钱)
     */
    public BigDecimal getLandPrice() {
        return landPrice;
    }

    /**
     * 设置土地价格 (1天多少钱)
     *
     * @param landPrice 土地价格 (1天多少钱)
     */
    public void setLandPrice(BigDecimal landPrice) {
        this.landPrice = landPrice;
    }

    /**
     * 获取创建时间
     *
     * @return land_createtime - 创建时间
     */
    public Date getLandCreatetime() {
        return landCreatetime;
    }

    /**
     * 设置创建时间
     *
     * @param landCreatetime 创建时间
     */
    public void setLandCreatetime(Date landCreatetime) {
        this.landCreatetime = landCreatetime;
    }

    /**
     * 获取更新时间
     *
     * @return land_updatetime - 更新时间
     */
    public Date getLandUpdatetime() {
        return landUpdatetime;
    }

    /**
     * 设置更新时间
     *
     * @param landUpdatetime 更新时间
     */
    public void setLandUpdatetime(Date landUpdatetime) {
        this.landUpdatetime = landUpdatetime;
    }
}