package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.ShowRequestParam;
import cn.chenyilei.work.domain.pojo.TbShow;
import cn.chenyilei.work.domain.vo.AjaxPageResult;
import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.FileService;
import cn.chenyilei.work.web.service.TbShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 注释
 * @see cn.chenyilei.work.domain.pojo.TbShow
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/20 16:11
 */
@Api("风采展示接口")
@RestController
@RequestMapping("/show")
public class TbShowController {

    @Autowired
    TbShowService tbShowService;
    @Autowired
    FileService fileService;

    @ApiOperation("查询自己的风采!")
    @GetMapping("/selectMyList")
    public AjaxResult<List<TbShow>> selectMyList(PageRequest pageRequest){
        List<TbShow> tbShows = tbShowService.selectMyList(pageRequest);
        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbShows)
                .pageRequest(pageRequest);
    }

    @ApiOperation("查询某个用户的风采!")
    @GetMapping("/selectOne/{userId}")
    public AjaxResult<List<TbShow>> selectList(@PathVariable("userId") Integer userId){
        TbShow tbShow = tbShowService.selectOneById(userId);
        return AjaxResult.success(tbShow,"查询成功!");
    }

    @ApiOperation("查询所有的风采!")
    @GetMapping("/selectAll")
    public AjaxResult<List<TbShow>> selectList(PageRequest pageRequest){
        List<TbShow> tbShows = tbShowService.selectAll(pageRequest);

        return AjaxPageResult
                .builder()
                .success(true)
                .msg("查询成功!")
                .data(tbShows)
                .pageRequest(pageRequest);
    }

    @ApiOperation("增加一个风采")
    @PostMapping("/insertOne")
    public AjaxResult insertOne(ShowRequestParam tbShow
                              , @RequestParam(value = "file",required = false) MultipartFile file) throws Exception {
        if (file != null) {
            String imageUrl = fileService.uploadImg(file);
            tbShow.setShowImage(imageUrl);
        }
        tbShowService.insertShow(tbShow);
        return AjaxResult.success(null,"增加成功!");
    }

    @ApiOperation("根据ID更新一个风采")
    @PutMapping("/updateOne")
    public AjaxResult updateOne(@RequestBody TbShow tbShow){
        tbShowService.updateOne(tbShow);
        return AjaxResult.success(null,"更新成功!");
    }

    @ApiOperation("删除一个风采")
    @DeleteMapping("/deleteOne/{id}")
    public AjaxResult deleteOne(@PathVariable("id")Integer id){
        tbShowService.deleteOne(id);
        return AjaxResult.success(null,"删除成功!");
    }


}
