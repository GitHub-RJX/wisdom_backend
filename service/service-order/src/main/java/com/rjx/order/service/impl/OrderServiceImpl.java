package com.rjx.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.rjx.edu.entity.frontVo.EduCourseInfoVo;
import com.rjx.order.client.EduClient;
import com.rjx.order.client.UcenterClient;
import com.rjx.order.entity.Order;
import com.rjx.order.mapper.OrderMapper;
import com.rjx.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjx.ucenter.entity.Ucenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String saveOrder(String courseId, String ucenterId) {
        Ucenter ucenter = ucenterClient.getById(ucenterId);
        EduCourseInfoVo eduCourseInfoVo = eduClient.getCourseInfoVoById(courseId);
        Order order = new Order();
        order.setOrderNo(IdWorker.get32UUID());
        order.setCourseId(courseId);
        order.setCourseTitle(eduCourseInfoVo.getTitle());
        order.setCourseCover(eduCourseInfoVo.getCover());
        order.setTeacherName(eduCourseInfoVo.getTeacherName());
        order.setTotalFee(eduCourseInfoVo.getPrice());
        order.setMemberId(ucenterId);
        order.setMobile(ucenter.getMobile());
        order.setNickname(ucenter.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
