package cn.chenyilei.work.domain.pojo.land;

import javax.persistence.*;

@Table(name = "tb_land_cart")
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
    private Integer number;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
     * 获取商品数量
     *
     * @return number - 商品数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置商品数量
     *
     * @param number 商品数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}