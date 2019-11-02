package cn.chenyilei.work.web.controller.land;

import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.PlantingRequestParam;
import cn.chenyilei.work.domain.pojo.TbShow;
import cn.chenyilei.work.domain.pojo.land.TbPlanting;
import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.FileService;
import cn.chenyilei.work.web.service.TbPlantingService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 种植信息
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/24 16:02
 */
@Api(tags = "TbPlantingController 种植信息接口")
@RestController
@RequestMapping("/planting")
public class TbPlantingController {
    @Autowired
    TbPlantingService plantingService;
    @Autowired
    FileService fileService;

    @ApiOperation("查询账号所有的种植信息![区分登陆账号]")
    @GetMapping("/selectAll")
    public AjaxResult<List<TbPlanting>> selectAll(PageRequest pageRequest,Boolean isCustomer){
        List<TbPlanting> tbPlantings =plantingService.selectAll(pageRequest,isCustomer);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbPlantings)
                .pageRequest(pageRequest);
    }
    @ApiOperation("查询田的具体种植信息")
    @GetMapping("/selectOneByLandId")
    public AjaxResult<List<TbPlanting>> selectOneByLandId(@RequestParam(name = "landId")Integer landId){
        List<TbPlanting> tbPlanting = plantingService.selectOneByLandId(landId);
        return AjaxResult.success(tbPlanting,"查询成功");
    }


    @ApiOperation("农户发布一条关于田的种植信息!")
    @PostMapping("/insertOne")
    public AjaxResult insertOne(PlantingRequestParam.insertOne param
            ,@RequestParam(name = "file",required = false) MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            String imageUrl = fileService.uploadImg(file);
            param.setPlantingImage(imageUrl);
        }
        plantingService.insertOne(param);
        return AjaxResult.success(true,"发布成功!");
    }


    @ApiOperation("农户删除一条种植信息")
    @DeleteMapping("/delete/{plantingId}")
    public AjaxResult delete(@PathVariable("plantingId")Integer plantingId){
        plantingService.deleteOne(plantingId);
        return AjaxResult.success(null,"删除成功!");
    }


}
