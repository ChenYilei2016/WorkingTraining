package cn.chenyilei.work.domain.pojo.land;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tb_bind_user_land")
public class TbBindUserLand {
    /**
     * 用户土地关系表ID
     */
    @Id
    @Column(name = "ul_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ulId;

    /**
     * 客户用户ID
     */
    @Column(name = "ul_buy_user_id")
    private Integer ulBuyUserId;

    /**
     * 农户用户ID
     */
    @Column(name = "ul_sell_user_id")
    private Integer ulSellUserId;

    /**
     * 土地ID
     */
    @Column(name = "ul_land_id")
    private Integer ulLandId;


}