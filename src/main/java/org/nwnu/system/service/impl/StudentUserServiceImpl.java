package org.nwnu.system.service.impl;

import org.nwnu.system.entity.StudentUser;
import org.nwnu.system.mapper.StudentUserMapper;
import org.nwnu.system.service.StudentUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Answer
 * @since 2018-05-13
 */
@Service
public class StudentUserServiceImpl extends ServiceImpl<StudentUserMapper, StudentUser> implements StudentUserService {
	
}
