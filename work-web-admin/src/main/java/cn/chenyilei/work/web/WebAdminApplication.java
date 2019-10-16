package cn.chenyilei.work.web;

import cn.chenyilei.work.commonutils.ZipUtils;
import cn.chenyilei.work.web.properties.SslProperties;
import cn.chenyilei.work.web.security.filter.FilterConfiguration;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.poi.excel.ExcelPicUtil;
import org.apache.catalina.Executor;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.coyote.ProtocolHandler;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.util.AntPathMatcher;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 服务启动类
 * @see cn.chenyilei.work.web.security.config.WebSecurityConfig 安全模块
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 14:08
 */
@SpringBootApplication(scanBasePackages = {
        "cn.chenyilei.work"
})
@MapperScan(basePackages = {
        "cn.chenyilei.work.domain.mapper",
        "cn.chenyilei.work.web.mapper"
})
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
                Ssl ssl = new Ssl();
                ssl.setKeyStore(sslProperties.getKeyStore());
                ssl.setKeyPassword(sslProperties.getKeyPassword());
                ssl.setKeyStoreType(sslProperties.getKeyStoreType());

                if( factory instanceof TomcatServletWebServerFactory ){
                    TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) factory;
                    tomcatServletWebServerFactory.setSsl(ssl);

                }else if(factory instanceof JettyServletWebServerFactory){
                    JettyServletWebServerFactory jettyServletWebServerFactory = (JettyServletWebServerFactory) factory;
                    jettyServletWebServerFactory.setSsl(ssl);
                }else{
                    throw new RuntimeException("没有适合的web容器能够使用SSL证书!");
                }

            }
        };
    }


}