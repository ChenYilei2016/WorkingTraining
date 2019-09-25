package cn.chenyilei.work.domain.pojo.land;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_planting")
@Data
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


}