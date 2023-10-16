package com.rjx.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjx.order.entity.Order;
import com.rjx.order.service.OrderService;
import com.rjx.utils.JwtUtils;
import com.rjx.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
//@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save/{courseId}")
    public Result saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        String orderNo = orderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return Result.success(orderNo);
    }

    @GetMapping("/getOrderByNo/{orderNo}")
    public Result getOrderByNo(@PathVariable String orderNo) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getOrderNo, orderNo);
        Order order = orderService.getOne(queryWrapper);
        return Result.success(order);
    }

    @GetMapping("/ifPayCourse/{courseId}/{memberId}")
    public boolean ifPayCourse(@PathVariable String courseId, @PathVariable String memberId) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getCourseId, courseId);
        queryWrapper.eq(Order::getMemberId, memberId);
        queryWrapper.eq(Order::getStatus, 1);
        queryWrapper.eq(Order::getIsDeleted, 0);
        long count = orderService.count(queryWrapper);
        return count > 0 ? true : false;
    }
}

