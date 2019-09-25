package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.constant.CodeResultEnum;
import cn.chenyilei.work.domain.dto.LandRequestParam;
import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.mapper.TbLandMapper;
import cn.chenyilei.work.domain.pojo.land.TbLand;
import cn.chenyilei.work.domain.pojo.internal_enum.CheckEnum;
import cn.chenyilei.work.domain.pojo.internal_enum.UserLevelEnum;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.exception.InvalidDoException;
import cn.chenyilei.work.web.service.TbLandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/23 14:09
 */
@Service
public class TbLandServiceImpl implements TbLandService {
    @Autowired
    TbLandMapper tbLandMapper;

    @Override
    public List<TbLand> selectAll(LandRequestParam landRequestParam, PageRequest pageRequest) {
        TbLand tbLand = new TbLand();
        BeanUtils.copyProperties(landRequestParam,tbLand);
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize());
        return tbLandMapper.select(tbLand);
    }

    @Override
    public List<TbLand> selectMyList(LandRequestParam landRequestParam, PageRequest pageRequest) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        TbLand tbLand = new TbLand();
        BeanUtils.copyProperties(landRequestParam,tbLand);
        tbLand.setLandUserId(Integer.valueOf(user.getUserId()));
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize());
        return tbLandMapper.select(tbLand);
    }

    @Override
    public TbLand selectOne(Integer landId, LandRequestParam landRequestParam, PageRequest pageRequest) {
        TbLand tbLand = new TbLand();
        BeanUtils.copyProperties(landRequestParam,tbLand);
        tbLand.setLandId(landId);
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize());
        return tbLandMapper.selectOne(tbLand);
    }

    @Override
    public void registerLand(TbLand tbLand) {
        tbLand.setLandIsRent(false);
        tbLand.setLandCreatetime(new Date());
        tbLand.setLandUpdatetime(new Date());
        tbLand.setLandStatus(CheckEnum.SUCCESS);

        //设置用户的ID
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        tbLand.setLandUserId(Integer.valueOf(user.getUserId()));

        tbLandMapper.insert(tbLand);
    }

    @Override
    public void updateLand(TbLand tbLand) {
        tbLand.setLandUpdatetime(new Date());
        tbLandMapper.updateByPrimaryKeySelective(tbLand);
    }

    /**
     * 已经租的田不能删除
     * @param landId
     */
    @Override
    @Transactional
    public void deleteLand(Integer landId) {
        TbLand tbLand = tbLandMapper.selectByPrimaryKey(landId);
        if(tbLand.getLandIsRent()){
            throw new InvalidDoException(CodeResultEnum.INVALID_DO,"土地已经被租!");
        }
        tbLand.setLandIsRent(false);
        int delete = tbLandMapper.delete(tbLand);
    }


    @Override
    public void setOnline(boolean isopen) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        boolean hasFarmer = user.getAuthorities().stream().anyMatch(x -> {
            return UserLevelEnum.FARMER.is(((GrantedAuthority) x).getAuthority());
        });
        if(!hasFarmer){
            throw new UnapprovedClientAuthenticationException("非农户!");
        }

        TbLand tbLand = new TbLand();
        tbLand.setLandUserId(Integer.valueOf(user.getUserId()));
        List<TbLand> tbLandList = tbLandMapper.select(tbLand);
        //设置状态
        tbLandList.stream().forEach(x->x.setLandIsOpen(isopen));
        tbLandMapper.insertList(tbLandList);
    }

    @Override
    public boolean getOnline() {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        TbLand tbLand = new TbLand();
        tbLand.setLandUserId(Integer.valueOf(user.getUserId()));
        TbLand result = tbLandMapper.selectOne(tbLand);
        return (result == null ) || result.getLandIsOpen();
    }


}
