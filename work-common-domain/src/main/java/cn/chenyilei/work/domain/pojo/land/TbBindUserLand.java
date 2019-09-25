package cn.chenyilei.work.domain.pojo.land;

import javax.persistence.*;

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
     * 用户ID
     */
    @Column(name = "ul_user_id")
    private Integer ulUserId;

    /**
     * 土地ID
     */
    @Column(name = "ul_land_id")
    private Integer ulLandId;

    /**
     * 获取用户土地关系表ID
     *
     * @return ul_id - 用户土地关系表ID
     */
    public Integer getUlId() {
        return ulId;
    }

    /**
     * 设置用户土地关系表ID
     *
     * @param ulId 用户土地关系表ID
     */
    public void setUlId(Integer ulId) {
        this.ulId = ulId;
    }

    /**
     * 获取用户ID
     *
     * @return ul_user_id - 用户ID
     */
    public Integer getUlUserId() {
        return ulUserId;
    }

    /**
     * 设置用户ID
     *
     * @param ulUserId 用户ID
     */
    public void setUlUserId(Integer ulUserId) {
        this.ulUserId = ulUserId;
    }

    /**
     * 获取土地ID
     *
     * @return ul_land_id - 土地ID
     */
    public Integer getUlLandId() {
        return ulLandId;
    }

    /**
     * 设置土地ID
     *
     * @param ulLandId 土地ID
     */
    public void setUlLandId(Integer ulLandId) {
        this.ulLandId = ulLandId;
    }
}