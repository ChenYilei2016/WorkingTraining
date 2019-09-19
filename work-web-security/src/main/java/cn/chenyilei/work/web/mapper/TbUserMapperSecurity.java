package cn.chenyilei.work.web.mapper;

import cn.chenyilei.work.domain.pojo.TbPermission;
import cn.chenyilei.work.domain.pojo.TbUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbUserMapperSecurity extends Mapper<TbUser> {
    public List<TbPermission> findPermissionsByUserId(@Param("userId") String userId);
}