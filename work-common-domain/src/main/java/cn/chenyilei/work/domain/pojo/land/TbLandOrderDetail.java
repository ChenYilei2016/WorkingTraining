package cn.chenyilei.work.domain.pojo.land;

import lombok.Data;

import javax.persistence.*;

@Table(name = "tb_land_order_detail")
@Data
public class TbLandOrderDetail {
    /**
     * 土地订单详情主键
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
     * 土地ID
     */
    @Column(name = "land_id")
    private Integer landId;

    /**
     * 土地名称
     */
    @Column(name = "land_name")
    private String landName;


    /**
     * 租的天数
     */
    @Column(name = "land_number")
    private Integer landNumber;


    @Column(name = "land_image")
    private String landImage;


    @Column(name = "land_price")
    private Integer landPrice;

}