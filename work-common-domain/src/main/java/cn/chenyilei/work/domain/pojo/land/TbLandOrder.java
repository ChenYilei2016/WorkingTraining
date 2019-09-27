package cn.chenyilei.work.domain.pojo.land;

import cn.chenyilei.work.domain.pojo.internal_enum.OrderStatusEnum;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_land_order")
@Data
public class TbLandOrder {
    /**
     * 唯一ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 买家ID
     */
    @Column(name = "buyer_id")
    private Integer buyerId;

    /**
     * 卖家ID
     */
    @Column(name = "seller_id")
    private Integer sellerId;

    /**
     * 订单价格
     */
    @Column(name = "price")
    private Integer price;

    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "updatetime")
    private Date updatetime;

    @Column(name = "orderstatus")
    private OrderStatusEnum orderstatus;


}