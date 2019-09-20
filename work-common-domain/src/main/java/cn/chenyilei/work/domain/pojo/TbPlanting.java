package cn.chenyilei.work.domain.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_planting")
public class TbPlanting {
    /**
     * 种植表ID
     */
    @Id
    @Column(name = "planting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plantingId;

    /**
     * 是哪一块田
     */
    @Column(name = "planting_land_id")
    private Integer plantingLandId;

    /**
     * 发布主要信息
     */
    @Column(name = "planting_information")
    private String plantingInformation;

    /**
     * 图片连接,可以为空
     */
    @Column(name = "planting_image")
    private String plantingImage;

    /**
     * 创建时间
     */
    @Column(name = "planting_createtime")
    private Date plantingCreatetime;

    /**
     * 更新时间
     */
    @Column(name = "planting_updatetime")
    private Date plantingUpdatetime;

    /**
     * 获取种植表ID
     *
     * @return planting_id - 种植表ID
     */
    public Integer getPlantingId() {
        return plantingId;
    }

    /**
     * 设置种植表ID
     *
     * @param plantingId 种植表ID
     */
    public void setPlantingId(Integer plantingId) {
        this.plantingId = plantingId;
    }

    /**
     * 获取是哪一块田
     *
     * @return planting_land_id - 是哪一块田
     */
    public Integer getPlantingLandId() {
        return plantingLandId;
    }

    /**
     * 设置是哪一块田
     *
     * @param plantingLandId 是哪一块田
     */
    public void setPlantingLandId(Integer plantingLandId) {
        this.plantingLandId = plantingLandId;
    }

    /**
     * 获取发布主要信息
     *
     * @return planting_information - 发布主要信息
     */
    public String getPlantingInformation() {
        return plantingInformation;
    }

    /**
     * 设置发布主要信息
     *
     * @param plantingInformation 发布主要信息
     */
    public void setPlantingInformation(String plantingInformation) {
        this.plantingInformation = plantingInformation;
    }

    /**
     * 获取图片连接,可以为空
     *
     * @return planting_image - 图片连接,可以为空
     */
    public String getPlantingImage() {
        return plantingImage;
    }

    /**
     * 设置图片连接,可以为空
     *
     * @param plantingImage 图片连接,可以为空
     */
    public void setPlantingImage(String plantingImage) {
        this.plantingImage = plantingImage;
    }

    /**
     * 获取创建时间
     *
     * @return planting_createtime - 创建时间
     */
    public Date getPlantingCreatetime() {
        return plantingCreatetime;
    }

    /**
     * 设置创建时间
     *
     * @param plantingCreatetime 创建时间
     */
    public void setPlantingCreatetime(Date plantingCreatetime) {
        this.plantingCreatetime = plantingCreatetime;
    }

    /**
     * 获取更新时间
     *
     * @return planting_updatetime - 更新时间
     */
    public Date getPlantingUpdatetime() {
        return plantingUpdatetime;
    }

    /**
     * 设置更新时间
     *
     * @param plantingUpdatetime 更新时间
     */
    public void setPlantingUpdatetime(Date plantingUpdatetime) {
        this.plantingUpdatetime = plantingUpdatetime;
    }
}