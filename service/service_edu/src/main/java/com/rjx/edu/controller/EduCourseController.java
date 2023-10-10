package com.rjx.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.edu.entity.EduCourse;
import com.rjx.edu.entity.vo.EduCourseInfo;
import com.rjx.edu.entity.vo.EduCoursePublishVo;
import com.rjx.edu.service.EduCourseService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edu/course")
//@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("/list")
    public Result getAll() {
        List<EduCourse> eduCourseList = eduCourseService.list();
        return Result.success(eduCourseList);
    }

    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable String id) {
        EduCourseInfo eduCourseInfo = eduCourseService.getInfoById(id);
        return Result.success(eduCourseInfo);
    }

    @PostMapping("/page/{current}/{size}")
    public Result getByPage(@PathVariable Long current, @PathVariable Long size, @RequestBody EduCourse eduCourse) {
        Page<EduCourse> eduCoursePage = new Page<>(current, size);
        LambdaQueryWrapper<EduCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduCourse::getIsDeleted, 0);
        queryWrapper.like(eduCourse.getTitle() != null && eduCourse.getTitle().length() != 0, EduCourse::getTitle, eduCourse.getTitle());
        queryWrapper.eq(eduCourse.getStatus() != null && eduCourse.getStatus().length() != 0, EduCourse::getStatus, eduCourse.getStatus());
        queryWrapper.orderByDesc(EduCourse::getGmtCreate);
        eduCourseService.page(eduCoursePage, queryWrapper);
        List<EduCourse> eduCourseList = eduCoursePage.getRecords();
        long total = eduCoursePage.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("eduCourseList", eduCourseList);
        map.put("total", total);
        return Result.success(map);
    }

    @PostMapping("/save")
    public Result save(@RequestBody EduCourseInfo eduCourseInfo) {
        String id = eduCourseService.saveInfo(eduCourseInfo);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return Result.success(map);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        LambdaQueryWrapper<EduCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduCourse::getId, id);
        eduCourseService.remove(queryWrapper);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody EduCourseInfo eduCourseInfo) {
        eduCourseService.updateInfo(eduCourseInfo);
        return Result.success();
    }

    @GetMapping("/beforePublish/{courseId}")
    public Result getEduCoursePublishVo(@PathVariable String courseId) {
        EduCoursePublishVo eduCoursePublishVo = eduCourseService.getEduCoursePublishVo(courseId);
        return Result.success(eduCoursePublishVo);
    }

    @PutMapping("/afterPublish/{courseId}")
    public Result updateAfterPublish(@PathVariable String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.success();
    }
}

