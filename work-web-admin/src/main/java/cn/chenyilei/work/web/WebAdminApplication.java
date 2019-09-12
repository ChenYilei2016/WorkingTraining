package cn.chenyilei.work.web;

import cn.chenyilei.work.web.constant.SslProperties;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 服务启动类
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 14:08
 */
@SpringBootApplication(scanBasePackages = "cn.chenyilei.work")
@MapperScan(basePackages = "cn.chenyilei.work.web.mapper")
public class WebAdminApplication implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebAdminApplication.class)
                .run(args);
    }
    @Override
    public void run(String... args) throws Exception {
        LoggerFactory.getLogger(WebAdminApplication.class).info("=======启动完成!=======");
    }

//  https 配置
    @Bean
    @ConditionalOnProperty(prefix = "cyl.ssl",name = "enabled-ssl",havingValue = "true",matchIfMissing = false)
    WebServerFactoryCustomizer webServerFactoryCustomizer(SslProperties sslProperties){
        return new WebServerFactoryCustomizer() {
            @Override
            public void customize(WebServerFactory factory) {
                if( factory instanceof TomcatServletWebServerFactory ){
                    TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) factory;
                    Ssl ssl = new Ssl();
                    ssl.setKeyStore(sslProperties.getKeyStore());
                    ssl.setKeyPassword(sslProperties.getKeyPassword());
                    ssl.setKeyStoreType(sslProperties.getKeyStoreType());
                    tomcatServletWebServerFactory.setSsl(ssl);
                }else{
                    throw new RuntimeException("没有适合的web容器能够使用SSL证书!");
                }
            }
        };
    }


}