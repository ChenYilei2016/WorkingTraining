package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.commonutils.MD5Util;
import cn.chenyilei.work.web.properties.SystemProperties;
import cn.chenyilei.work.web.service.FileService;
import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.digest.MD5;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @see cn.chenyilei.work.web.service.FileService
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/19 14:28
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    SystemProperties systemProperties;
    private static MD5 md5utils = new MD5();

    @Override
    public String uploadImg(MultipartFile multipartFile) throws Exception{
//        System.out.println(multipartFile.getName());
//        System.out.println(multipartFile.getOriginalFilename());
//        System.out.println(multipartFile.getSize());
        return uploadImg(multipartFile.getBytes(),multipartFile.getOriginalFilename());
    }

    @Override
    public String uploadImg(byte[] bytes, String filename) throws Exception {
        if(bytes.length < 1){
            throw new RuntimeException("上传的图片大小有问题!");
        }

        String suffix = StringUtils.substringAfterLast(filename,"."); //文件后缀 .jpg  ....

        String fileMd5Name = md5utils.digestHex(bytes); //文件md5名字
        String baseUrl = ""+fileMd5Name.charAt(0)+"/"+fileMd5Name+"."+suffix; //基础路径 : s/filename.jpg 需要判断有无文件夹
        File uploadFile = new File(systemProperties.getUploadImgDirPerfix()+baseUrl); //完整文件

        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
        }
        //上传文件
        FileOutputStream fileOutputStream = new FileOutputStream(uploadFile);
        IOUtils.write(bytes,fileOutputStream);

        return systemProperties.getVisitImgDirPerfix()+baseUrl;
    }

}
