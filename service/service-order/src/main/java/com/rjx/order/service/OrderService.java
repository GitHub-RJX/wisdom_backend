package com.rjx.order.service;

import com.rjx.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author rjx
 * @since 2023-10-06
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String courseId, String ucenterId);
}
