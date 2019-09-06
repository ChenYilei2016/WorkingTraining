package cn.chenyilei.work.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 服务启动类
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 14:08
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.chenyilei.work.web.mapper")
public class WebAdminApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WebAdminApplication.class)
                .run(args);
         LoggerFactory.getLogger(WebAdminApplication.class).error("aaa");
    }

}
