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
    S dao;

    @Override
    public int insert(T t) {
        return dao.insert(t);
    }

    @Override
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int update(T t) {
        return dao.updateByPrimaryKey(t);
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> select(T t) {
        return dao.select(t);
    }

    @Transactional(readOnly = true)
    @Override
    public T selectById(int id) {
        return dao.selectByPrimaryKey(id);
    }
}
