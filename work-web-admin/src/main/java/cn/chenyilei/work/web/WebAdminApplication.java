package cn.chenyilei.work.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 服务启动类
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 14:08
 */
@SpringBootApplication
public class WebAdminApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WebAdminApplication.class)
                .run(args);
    }
}
