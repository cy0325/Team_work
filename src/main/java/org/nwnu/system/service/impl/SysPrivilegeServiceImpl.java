package org.nwnu.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.nwnu.system.entity.SysPrivilege;
import org.nwnu.system.mapper.SysPrivilegeMapper;
import org.nwnu.system.service.SysPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author 董晓辉
 * @since 2017-03-07
 */
@Service
public class SysPrivilegeServiceImpl extends ServiceImpl<SysPrivilegeMapper, SysPrivilege> implements SysPrivilegeService {

	@Autowired
	private SysPrivilegeMapper sysPrivilegeMapper;	
	
	@Override
    public String getCode() {
        String conStr = sysPrivilegeMapper.getCode();
        if(conStr != null){
            return conStr;
        } else{
            return "0001";
        }
    }
}
