package cn.chenyilei.work.web.security.processor;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 12:38
 */
public class ProcessorTypeTest {

    @Test
    public void s() {
        System.out.println(ProcessorType.toProcessorType("dafdas/wx"));
        System.out.println(AuthenticationFilterProcessor.class.getSimpleName());

    }
}