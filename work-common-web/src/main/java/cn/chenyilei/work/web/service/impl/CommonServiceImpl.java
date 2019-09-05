package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.web.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:23
 */
@Transactional(readOnly = false)
public class CommonServiceImpl <T,S extends Mapper<T>> implements CommonService<T> {

    @Autowired
    S s;

    @Override
    public int insert(T t) {
        return s.insert(t);
    }

    @Override
    public int delete(T t) {
        return s.delete(t);
    }

    @Override
    public int update(T t) {
        return s.updateByPrimaryKey(t);
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> select(T t) {
        return s.select(t);
    }
}
