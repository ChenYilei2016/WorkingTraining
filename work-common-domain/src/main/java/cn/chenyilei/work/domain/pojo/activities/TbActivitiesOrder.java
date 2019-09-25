package cn.chenyilei.work.domain.pojo.activities;

import cn.chenyilei.work.domain.pojo.internal_enum.OrderStatusEnum;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_activities_order")
public class TbActivitiesOrder {
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
    private Integer price;

    private Date createtime;

    private Date updatetime;

    private OrderStatusEnum orderstatus;

    /**
     * 获取唯一ID
     *
     * @return id - 唯一ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置唯一ID
     *
     * @param id 唯一ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取买家ID
     *
     * @return buyer_id - 买家ID
     */
    public Integer getBuyerId() {
        return buyerId;
    }

    /**
     * 设置买家ID
     *
     * @param buyerId 买家ID
     */
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * 获取卖家ID
     *
     * @return seller_id - 卖家ID
     */
    public Integer getSellerId() {
        return sellerId;
    }

    /**
     * 设置卖家ID
     *
     * @param sellerId 卖家ID
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取订单价格
     *
     * @return price - 订单价格
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置订单价格
     *
     * @param price 订单价格
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return orderstatus
     */
    public OrderStatusEnum getOrderstatus() {
        return orderstatus;
    }

    /**
     * @param orderstatus
     */
    public void setOrderstatus(OrderStatusEnum orderstatus) {
        this.orderstatus = orderstatus;
    }
}