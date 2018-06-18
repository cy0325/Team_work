package org.nwnu.system.service;

import java.util.Date;

import org.nwnu.system.entity.SysRolePrivilege;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author 董晓辉
 * @since 2017-03-07
 */
public interface SysRolePrivilegeService extends IService<SysRolePrivilege> {
	boolean roletgrant(String privilegelist, String rolecode,String operator,Date updateDate);
}
