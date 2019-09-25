package cn.chenyilei.work.domain.pojo.activities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "tb_activities_cart")
@Data
public class TbActivitiesCart {
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
     * 活动的id
     */
    @Column(name = "activities_id")
    private Integer activitiesId;

    /**
     * 商品数量
     */
    @Column(name = "number")
    private Integer number;

    @Column(name = "image")
    private String image;
}