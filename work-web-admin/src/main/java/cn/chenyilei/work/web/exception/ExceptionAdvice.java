package cn.chenyilei.work.web.exception;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局捕获异常处理
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/18 15:38
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(value = AccessDeniedException.class)
    public AjaxResult toAccessDeniedExceptionClass(Exception e){
        log.error("toAccessDeniedExceptionClass");
        return AjaxResult.error(e.getMessage(), CodeResultEnum.FORBIDDEN);
    }

    @ExceptionHandler(value = Exception.class)
    public AjaxResult toExceptionClass(Exception e){
        log.error("toExceptionClass");
        if(System.getProperty("isDebug")!= null){
            e.printStackTrace();
        }
        return AjaxResult.error(e.getMessage());
    }


}
