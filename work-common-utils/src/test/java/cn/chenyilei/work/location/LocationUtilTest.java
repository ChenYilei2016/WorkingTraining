package cn.chenyilei.work.location;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/09 11:27
 */
public class LocationUtilTest {

    @Test
    public void getLocationByIP() {
        System.out.println(LocationUtil.getLocationByIP("106.12.74.218"));
    }
}