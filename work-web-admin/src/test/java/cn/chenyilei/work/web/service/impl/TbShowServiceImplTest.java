package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.mapper.TbShowMapper;
import cn.chenyilei.work.domain.pojo.TbShow;
import cn.chenyilei.work.web.WebAdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 13:09
 */
@SpringBootTest(classes = {WebAdminApplication.class} )
@RunWith(SpringRunner.class)
public class TbShowServiceImplTest {
    @Autowired
    TbShowMapper tbShowMapper;

    @Test
    public void insertShow() {
        TbShow tbShow = new TbShow();
        tbShow.setShowUpdatetime(new Date());
        tbShow.setShowCreatetime(new Date());
        tbShowMapper.insert(tbShow);
    }
}