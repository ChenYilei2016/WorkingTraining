package cn.chenyilei.work.domain.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_show")
public class TbShow {
    /**
     * 唯一Id
     */
    @Id
    @Column(name = "show_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

    /**
     * 发表用户的id
     */
    @Column(name = "show_user_id")
    private Integer showUserId;

    /**
     * 发布主要信息
     */
    @Column(name = "show_information")
    private String showInformation;

    /**
     * 图片
     */
    @Column(name = "show_image")
    private String showImage;

    /**
     * 创建时间
     */
    @Column(name = "show_createtime")
    private Date showCreatetime;

    /**
     * 更新时间
     */
    @Column(name = "show_updatetime")
    private Date showUpdatetime;

    /**
     * 获取唯一Id
     *
     * @return show_id - 唯一Id
     */
    public Integer getShowId() {
        return showId;
    }

    /**
     * 设置唯一Id
     *
     * @param showId 唯一Id
     */
    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    /**
     * 获取发表用户的id
     *
     * @return show_user_id - 发表用户的id
     */
    public Integer getShowUserId() {
        return showUserId;
    }

    /**
     * 设置发表用户的id
     *
     * @param showUserId 发表用户的id
     */
    public void setShowUserId(Integer showUserId) {
        this.showUserId = showUserId;
    }

    /**
     * 获取发布主要信息
     *
     * @return show_information - 发布主要信息
     */
    public String getShowInformation() {
        return showInformation;
    }

    /**
     * 设置发布主要信息
     *
     * @param showInformation 发布主要信息
     */
    public void setShowInformation(String showInformation) {
        this.showInformation = showInformation;
    }

    /**
     * 获取图片
     *
     * @return show_image - 图片
     */
    public String getShowImage() {
        return showImage;
    }

    /**
     * 设置图片
     *
     * @param showImage 图片
     */
    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    /**
     * 获取创建时间
     *
     * @return show_createtime - 创建时间
     */
    public Date getShowCreatetime() {
        return showCreatetime;
    }

    /**
     * 设置创建时间
     *
     * @param showCreatetime 创建时间
     */
    public void setShowCreatetime(Date showCreatetime) {
        this.showCreatetime = showCreatetime;
    }

    /**
     * 获取更新时间
     *
     * @return show_updatetime - 更新时间
     */
    public Date getShowUpdatetime() {
        return showUpdatetime;
    }

    /**
     * 设置更新时间
     *
     * @param showUpdatetime 更新时间
     */
    public void setShowUpdatetime(Date showUpdatetime) {
        this.showUpdatetime = showUpdatetime;
    }
}