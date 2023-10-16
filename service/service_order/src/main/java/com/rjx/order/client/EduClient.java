package com.rjx.order.client;

import com.rjx.edu.entity.frontVo.EduCourseInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-edu")
public interface EduClient {
    @GetMapping("/edu/front/course/getCourseInfoVoById/{id}")
    public EduCourseInfoVo getCourseInfoVoById(@PathVariable String id);
}
