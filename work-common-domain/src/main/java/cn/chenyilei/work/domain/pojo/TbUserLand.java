package cn.chenyilei.work.domain.pojo;

import javax.persistence.*;

@Table(name = "tb_user_land")
public class TbUserLand {
    /**
     * 用户土地关系表ID
     */
    @Id
    @Column(name = "user_land_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userLandId;

    /**
     * 用户ID
     */
    @Column(name = "user_land_user_id")
    private Integer userLandUserId;

    /**
     * 土地ID
     */
    @Column(name = "user_land_land_id")
    private Integer userLandLandId;

    /**
     * 获取用户土地关系表ID
     *
     * @return user_land_id - 用户土地关系表ID
     */
    public Integer getUserLandId() {
        return userLandId;
    }

    /**
     * 设置用户土地关系表ID
     *
     * @param userLandId 用户土地关系表ID
     */
    public void setUserLandId(Integer userLandId) {
        this.userLandId = userLandId;
    }

    /**
     * 获取用户ID
     *
     * @return user_land_user_id - 用户ID
     */
    public Integer getUserLandUserId() {
        return userLandUserId;
    }

    /**
     * 设置用户ID
     *
     * @param userLandUserId 用户ID
     */
    public void setUserLandUserId(Integer userLandUserId) {
        this.userLandUserId = userLandUserId;
    }

    /**
     * 获取土地ID
     *
     * @return user_land_land_id - 土地ID
     */
    public Integer getUserLandLandId() {
        return userLandLandId;
    }

    /**
     * 设置土地ID
     *
     * @param userLandLandId 土地ID
     */
    public void setUserLandLandId(Integer userLandLandId) {
        this.userLandLandId = userLandLandId;
    }
}