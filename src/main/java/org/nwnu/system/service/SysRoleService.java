package org.nwnu.system.service;

import org.nwnu.system.entity.SysRole;

import com.baomidou.mybatisplus.service.IService;

/**
 *
 * sysorle 表数据服务层接口
 *
 */
public interface SysRoleService extends IService<SysRole> {
	 String getCode();
}