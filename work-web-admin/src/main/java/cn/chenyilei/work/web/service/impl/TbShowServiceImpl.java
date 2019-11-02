package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.mapper.TbShowMapper;
import cn.chenyilei.work.domain.pojo.TbShow;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.service.TbShowService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/20 16:26
 */
@Service
public class TbShowServiceImpl implements TbShowService {

    @Autowired
    TbShowMapper tbShowMapper;

    @Override
    public TbShow selectOneById(Integer id) {
        return tbShowMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<TbShow> selectAll(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize(),false);
        PageHelper.orderBy("show_createtime desc");

        List<TbShow> tbShows = tbShowMapper.selectAll();
        return tbShows;
    }

    @Override
    public List<TbShow> selectMyList(PageRequest pageRequest) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize(),false);
        PageHelper.orderBy("show_createtime desc");

        TbShow tbShow = new TbShow();
        tbShow.setShowUserId(Integer.valueOf(user.getUserId()));
        List<TbShow> tbShows = tbShowMapper.select(tbShow);
        return tbShows;
    }

    @Override
    public void insertShow(TbShow tbShow) {
        tbShow.setShowCreatetime(new Date());
        tbShow.setShowUpdatetime(new Date());
        AuthenticationUser authenticationUser = SecurityContext.getSecurityContextPrincipal();
        tbShow.setShowUserId(Integer.valueOf(authenticationUser.getUserId()));
        tbShow.setShowUserName(authenticationUser.getUsername());
        tbShowMapper.insert(tbShow);
    }

    @Override
    public void updateOne(TbShow tbShow) {
        tbShow.setShowUpdatetime(new Date());
        tbShowMapper.updateByPrimaryKeySelective(tbShow);
    }

    @Override
    public void deleteOne(Integer id) {
        tbShowMapper.deleteByPrimaryKey(id);
    }


}
