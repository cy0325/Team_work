package org.nwnu.system.mapper;

import org.apache.ibatis.annotations.Param;
import org.nwnu.system.entity.SysPrivilege;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 权限表 Mapper 接口
 * </p>
 *
 * @author 董晓辉
 * @since 2017-03-07
 */
public interface SysPrivilegeMapper extends BaseMapper<SysPrivilege> {	
	String getCode();
	String getParentCode();
}