package org.nwnu.system.service.impl;

import org.nwnu.system.entity.SysRole;
import org.nwnu.system.mapper.SysRoleMapper;
import org.nwnu.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;



/**
 *
 * SysRole 表数据服务层接口实现类
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysroleMapper;	
	
	public String getCode() {
		String obj=sysroleMapper.getCode();
		 if (obj != null)
        {
            return obj;
        }
        else
        {
            return "0001";
        }	
	}
}