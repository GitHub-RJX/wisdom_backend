package com.rjx.order.service;

import com.rjx.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author rjx
 * @since 2023-10-06
 */
public interface PayLogService extends IService<PayLog> {

    Map<String, String> createNative(String orderNo);

    Map<String, String> getPayStatus(String orderNo);

    void updateStatus(Map<String, String> map);
}
