package cn.chenyilei.work.domain.pojo.land;

import lombok.Data;

import javax.persistence.*;

@Table(name = "tb_land_cart")
@Data
public class TbLandCart {
    /**
     * 唯一ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 土地ID
     */
    @Column(name = "land_id")
    private Integer landId;

    /**
     * 商品数量
     */
    @Column(name = "number")
    private Integer number;

    @Column(name = "name")
    private String name ;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Integer price;

}