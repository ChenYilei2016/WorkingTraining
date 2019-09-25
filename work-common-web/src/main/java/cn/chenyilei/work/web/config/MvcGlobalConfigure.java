package cn.chenyilei.work.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局编码设置
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/09 16:02
 */
@Configuration
@ConditionalOnProperty(prefix = "aaa",name = "aa",havingValue = "1")
public class MvcGlobalConfigure implements WebMvcConfigurer {

    private class CharsetCodeInterceptor extends HandlerInterceptorAdapter{
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            return true;
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CharsetCodeInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("*");
    }
}
