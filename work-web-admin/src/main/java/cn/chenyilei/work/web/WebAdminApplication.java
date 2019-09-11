package cn.chenyilei.work.web;

import cn.chenyilei.work.web.security.constant.WebSecurityProperties;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
        LoggerFactory.getLogger(WebAdminApplication.class).info("=======启动完成!========");
    }

//  https 配置
    @Bean
    @ConditionalOnProperty(prefix = "cyl.ssl",name = "enabled-ssl",havingValue = "true",matchIfMissing = false)
    WebServerFactoryCustomizer webServerFactoryCustomizer(WebSecurityProperties webSecurityProperties){
        return new WebServerFactoryCustomizer() {
            @Override
            public void customize(WebServerFactory factory) {
                TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) factory;
                Ssl ssl = new Ssl();
                ssl.setKeyStore(webSecurityProperties.getSsl().getKeyStore());
                ssl.setKeyPassword(webSecurityProperties.getSsl().getKeyPassword());
                ssl.setKeyStoreType(webSecurityProperties.getSsl().getKeyStoreType());
                tomcatServletWebServerFactory.setSsl(ssl);
            }
        };
    }


}