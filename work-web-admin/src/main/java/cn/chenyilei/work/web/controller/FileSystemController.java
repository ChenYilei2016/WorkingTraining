package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.properties.SystemProperties;
import cn.chenyilei.work.web.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

/**
 * 对于文件操作的接口
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/19 14:25
 */
@RestController
@RequestMapping("/file")
public class FileSystemController {
    @Autowired
    SystemProperties systemProperties;
    @Autowired
    FileService fileService;

    private static Random random = new Random();

    @ApiOperation("上传图片表单key 为 file")
    @PostMapping("/upload")
    public AjaxResult uploadImg(@RequestParam("file") MultipartFile multipartFile){
        String visitUrl = null;
        try {
            visitUrl = fileService.uploadImg(multipartFile);
        } catch (Exception e) {
            AjaxResult.success(null,"上传图片失败!");
        }
        return AjaxResult.success(visitUrl,"上传图片成功!");
    }

}
