package cn.chenyilei.work.web.security.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于从众多 {@link AuthenticationFilterProcessor}中找到需要的执行器
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 12:11
 */
@Component
public class AuthenticationFilterProcessorContextHolder {

    @Autowired(required = false)
    Map<String, AuthenticationFilterProcessor> filterProcessorMap = new HashMap<>();

    public AuthenticationFilterProcessor getProcessor(String name) {
        AuthenticationFilterProcessor authenticationFilterProcessor = filterProcessorMap.get(name);
        if(authenticationFilterProcessor == null){
            throw new InternalAuthenticationServiceException("找不到相应的AuthenticationFilterProcessor");
        }
        return authenticationFilterProcessor;
    }

    public AuthenticationFilterProcessor findFilterProcessorByUrl(String url)  {
        if(!ProcessorType.isProcessorType(url)){
            throw new UnapprovedClientAuthenticationException("错误的Url,找不到对应的执行器!");
        }
        ProcessorType type = ProcessorType.toProcessorType(url);
        return getProcessor(type.name() + AuthenticationFilterProcessor.class.getSimpleName());
    }
    public AuthenticationFilterProcessor findFilterProcessorByRequest(HttpServletRequest request)  {
        return findFilterProcessorByUrl( request.getRequestURI() );
    }

    public AuthenticationFilterProcessor findFilterProcessorByProcessorType(ProcessorType type) {
        return getProcessor(type.name() + AuthenticationFilterProcessor.class.getSimpleName());
    }
}
