package cn.chenyilei.work.web.service.impl;

import cn.chenyilei.work.domain.dto.PageRequest;
import cn.chenyilei.work.domain.dto.PlantingRequestParam;
import cn.chenyilei.work.domain.mapper.TbPlantingMapper;
import cn.chenyilei.work.domain.pojo.internal_enum.UserLevelEnum;
import cn.chenyilei.work.domain.pojo.land.TbPlanting;
import cn.chenyilei.work.domain.security.AuthenticationUser;
import cn.chenyilei.work.security.SecurityContext;
import cn.chenyilei.work.web.service.TbPlantingService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/25 14:28
 */
@Service
public class TbPlantingServiceImpl implements TbPlantingService {
    @Autowired
    TbPlantingMapper tbPlantingMapper;


    @Override
    public List<TbPlanting> selectAll(PageRequest pageRequest) {
        AuthenticationUser user = SecurityContext.getSecurityContextPrincipal();
        String userId = user.getUserId();
        //区分农户或者客户
        boolean isCustomer = user.getAuthorities().stream().anyMatch(x -> {
            return ((GrantedAuthority) x).getAuthority().equals(UserLevelEnum.CUSTOMER.name());
        });
        if(isCustomer){
            return tbPlantingMapper.selectAllWithCustomer(userId);
        }else{
            return tbPlantingMapper.selectAllWithFarmer(userId);
        }
    }

    @Override
    public void insertOne(PlantingRequestParam.insertOne param) {
        TbPlanting tbPlanting = new TbPlanting();
        tbPlanting.setPlantingInformation(param.getPlantingInformation());
        tbPlanting.setPlantingLandId(param.getPlantingLandId());
        tbPlanting.setPlantingImage(param.getPlantingImage());
        tbPlanting.setPlantingCreatetime(new Date());
        tbPlanting.setPlantingUpdatetime(new Date());
        tbPlantingMapper.insertSelective(tbPlanting);
    }

    @Override
    public void deleteOne(Integer plantingId) {
        tbPlantingMapper.deleteByPrimaryKey(plantingId);
    }
}
