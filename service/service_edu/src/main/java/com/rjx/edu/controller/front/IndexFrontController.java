package com.rjx.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjx.edu.entity.EduCourse;
import com.rjx.edu.entity.EduTeacher;
import com.rjx.edu.service.EduCourseService;
import com.rjx.edu.service.EduTeacherService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/edu/front")
//@CrossOrigin
public class IndexFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/courseList")
    public Result getAllCourse() {
        LambdaQueryWrapper<EduCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(EduCourse::getId);
        queryWrapper.last("limit 4");
        List<EduCourse> eduCourseList = eduCourseService.list(queryWrapper);
        return Result.success(eduCourseList);
    }

    @GetMapping("/teacherList")
    public Result getAllTeacher() {
        LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(EduTeacher::getId);
        queryWrapper.last("limit 4");
        List<EduTeacher> eduTeacherList = eduTeacherService.list(queryWrapper);
        return Result.success(eduTeacherList);
    }
}
