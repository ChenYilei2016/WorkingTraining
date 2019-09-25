package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.mapper.TbLandMapper;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.pojo.user.TbUser;
import cn.chenyilei.work.web.WebAdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 18:58
 */
@SpringBootTest(classes = {WebAdminApplication.class} )
@RunWith(SpringRunner.class)
public class TbLandServiceImplTest {
    @Autowired
    TbLandMapper tbLandMapper;

    @Test
    public void selectAll() {
        TbLand tbLand = new TbLand();
        System.out.println(tbLandMapper.select(tbLand));

    }


}