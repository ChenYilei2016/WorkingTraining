package cn.chenyilei.work.web.service;

import java.util.List;

/**
 * 通用service接口
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:02
 */
public interface CommonService<T> {
    public int insert(T t);

    public int delete(T t);
    public int deleteById(int id);

    public int update(T t);
    public List<T> select(T t);
    public T selectById(int id);

}
