package cn.chenyilei.work.web.security.processor;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 执行器的类型
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 12:16
 */
public enum ProcessorType {
    //微信类型验证
    wx,
    form,
    ;
    public static boolean isProcessorType(String url){
        ProcessorType type = null;
        try {
            String s = StringUtils.substringAfterLast(url, "/").toLowerCase();
            type = ProcessorType.valueOf(s);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public static ProcessorType toProcessorType(String url){
        ProcessorType type = null;
        try {
            String s = StringUtils.substringAfterLast(url, "/").toLowerCase();
            type = ProcessorType.valueOf(s);
        }catch (Exception e){
            return null;
        }
        return type;
    }

}
