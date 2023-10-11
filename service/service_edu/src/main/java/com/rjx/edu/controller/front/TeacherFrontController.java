package com.rjx.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.edu.entity.EduCourse;
import com.rjx.edu.entity.EduTeacher;
import com.rjx.edu.service.EduCourseService;
import com.rjx.edu.service.EduTeacherService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edu/front/teacher")
//@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("/getByPage/{current}/{size}")
    public Result getByPage(@PathVariable Long current, @PathVariable Long size) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduTeacher::getIsDeleted, 0);
        queryWrapper.orderByDesc(EduTeacher::getSort);
        eduTeacherService.page(eduTeacherPage, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        List<EduTeacher> eduTeacherList = eduTeacherPage.getRecords();
        long pages = eduTeacherPage.getPages();
        long total = eduTeacherPage.getTotal();
        boolean hasNext = eduTeacherPage.hasNext();
        boolean hasPrevious = eduTeacherPage.hasPrevious();
        map.put("eduTeacherList", eduTeacherList);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return Result.success(map);
    }

    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        LambdaQueryWrapper<EduCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduCourse::getTeacherId, id);
        queryWrapper.eq(EduCourse::getIsDeleted, 0);
        queryWrapper.orderByDesc(EduCourse::getBuyCount);
        List<EduCourse> eduCourseList = eduCourseService.list(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("eduTeacher", eduTeacher);
        map.put("eduCourseList", eduCourseList);
        return Result.success(map);
    }
}
