package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.mapper.TbActivitiesMapper;
import cn.chenyilei.work.domain.pojo.activities.TbActivities;
import cn.chenyilei.work.web.WebAdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import static org.junit.Assert.*;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/29 10:02
 */
@SpringBootTest(classes = {WebAdminApplication.class} )
@RunWith(SpringRunner.class)
public class TbActivitiesServiceImplTest {
    @Autowired
    TbActivitiesMapper tbActivitiesMapper;

    @Test
    public void less(){
        System.err.println(tbActivitiesMapper.lessNumber(1, 10));
    }

    @Test
    public void deleteActivities() {
        System.out.println(tbActivitiesMapper.hasStore(1));
        System.out.println(tbActivitiesMapper.queryCustomerList(2));
    }
}