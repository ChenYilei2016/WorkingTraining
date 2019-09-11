package cn.chenyilei.work.wx;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/11 15:12
 */
public class WxSmallProgramUtilsTest {

    @Test
    public void getOpenIdFromCode() {
        System.out.println(WxSmallProgramUtils.getOpenIdFromCode("011QcBch0LXmwv1euXeh0Whvch0QcBcO"));
    }
}