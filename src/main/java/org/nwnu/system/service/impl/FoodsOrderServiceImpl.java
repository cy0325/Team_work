package org.nwnu.system.service.impl;

import org.nwnu.system.entity.FoodsOrder;
import org.nwnu.system.mapper.FoodsOrderMapper;
import org.nwnu.system.service.FoodsOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Answer
 * @since 2018-05-13
 */
@Service
public class FoodsOrderServiceImpl extends ServiceImpl<FoodsOrderMapper, FoodsOrder> implements FoodsOrderService {
	
}
