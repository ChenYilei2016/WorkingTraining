package cn.chenyilei.work.web.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件处理
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/19 14:28
 */
public interface FileService {
    public String uploadImg(MultipartFile multipartFile) throws Exception;
    public String uploadImg(byte[] bytes, String filename) throws Exception;
}
