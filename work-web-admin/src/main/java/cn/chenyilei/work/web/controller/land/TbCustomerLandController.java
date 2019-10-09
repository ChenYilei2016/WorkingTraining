package cn.chenyilei.work.web.controller.land;

import cn.chenyilei.work.domain.dto.LandRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.service.TbLandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 13:46
 */
@Api(tags = "TbCustomerLandController 客户田地相关接口")
@RestController
@RequestMapping("/customer/land")
public class TbCustomerLandController {

    @Autowired
    TbLandService tbLandService;

    @ApiOperation("查询固定ID的田")
    @GetMapping("/select/{landId}")
    public AjaxResult<TbLand> selectOne(@PathVariable("landId") Integer landId
            ,LandRequestParam landRequestParam, PageRequest pageRequest){
        TbLand tbLand = tbLandService.selectOne(landId,landRequestParam,pageRequest);
        return AjaxResult.success(tbLand,"查询成功!");
    }

    @ApiOperation("客户查询所有的田")
    @GetMapping("/selectAll")
    public AjaxResult<List<TbLand>> selectAll(LandRequestParam landRequestParam, PageRequest pageRequest){
        //客户看审核成功的田
        landRequestParam.setLandStatus(CheckEnum.SUCCESS);
        List<TbLand> tbLands = tbLandService.selectAll(landRequestParam,pageRequest);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbLands)
                .pageRequest(pageRequest);
    }

    @ApiOperation("客户查询自己的田!")
    @GetMapping("/selectMyList")
    public AjaxResult<List<TbLand>> selectMyList(LandRequestParam landRequestParam,PageRequest pageRequest){
        List<TbLand> tbLandList = tbLandService.selectCustomerList(landRequestParam,pageRequest);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbLandList)
                .pageRequest(pageRequest);
    }



}

