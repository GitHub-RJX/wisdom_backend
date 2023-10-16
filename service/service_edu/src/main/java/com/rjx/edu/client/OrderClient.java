package com.rjx.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-order")
public interface OrderClient {
    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("/order/ifPayCourse/{courseId}/{memberId}")
    public boolean ifPayCourse(@PathVariable String courseId, @PathVariable String memberId);
}
