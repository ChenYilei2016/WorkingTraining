package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.domain.mapper.TbActivitiesMapper;
import cn.chenyilei.work.domain.mapper.TbLandMapper;
import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.domain.vo.RecommendedProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/10/22 12:47
 */
@Api(tags = "RecommendedController 推荐接口")
@RestController
@RequestMapping("/recommended")
public class RecommendedController {
    @Autowired
    TbLandMapper tbLandMapper;

    @Autowired
    TbActivitiesMapper tbActivitiesMapper;



    @GetMapping("/topHot")
    @ApiOperation("取最热的一块田,和一个活动!")
    public AjaxResult<List<RecommendedProductVo>> topHot(){

        List<RecommendedProductVo> list = new ArrayList<>();
        TbLand tbLand = tbLandMapper.randOne();

        if(null != tbLand){
            RecommendedProductVo recommendedProductVo = new RecommendedProductVo();
            recommendedProductVo.setId(tbLand.getLandId());
            recommendedProductVo.setImage(tbLand.getLandImage());
            recommendedProductVo.setInformation(tbLand.getLandInformation());
            recommendedProductVo.setPrice(tbLand.getLandPrice());
            recommendedProductVo.setType("land");
            list.add(recommendedProductVo);
        }
        TbActivities tbActivities = tbActivitiesMapper.randOne();
        if(null != tbActivities){
            RecommendedProductVo recommendedProductVo = new RecommendedProductVo();
            recommendedProductVo.setId(tbActivities.getActivitiesId());
            recommendedProductVo.setImage(tbActivities.getActivitiesImage());
            recommendedProductVo.setInformation(tbActivities.getActivitiesInformation());
            recommendedProductVo.setPrice(tbActivities.getActivitiesPrice());
            recommendedProductVo.setType("activities");
            list.add(recommendedProductVo);
        }
        return AjaxResult.success(list,"成功!");
    }

}
