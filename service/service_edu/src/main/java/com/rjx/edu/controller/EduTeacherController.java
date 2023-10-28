package com.rjx.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.edu.entity.EduChapter;
import com.rjx.edu.entity.EduCourse;
import com.rjx.edu.entity.EduTeacher;
import com.rjx.edu.entity.EduVideo;
import com.rjx.edu.service.EduChapterService;
import com.rjx.edu.service.EduCourseService;
import com.rjx.edu.service.EduTeacherService;
import com.rjx.edu.service.EduVideoService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edu/teacher")
//@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduVideoService eduVideoService;

    @GetMapping("/list")
    public Result getAll() {
        List<EduTeacher> eduTeacherList = eduTeacherService.list();
        return Result.success(eduTeacherList);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.success(eduTeacher);
    }

    @PostMapping("/page/{current}/{size}")
    public Result getByPage(@PathVariable Long current, @PathVariable Long size, @RequestBody EduTeacher eduTeacher) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduTeacher::getIsDeleted, 0)
                .like(eduTeacher.getName() != null && eduTeacher.getName().length() != 0, EduTeacher::getName, eduTeacher.getName())
                .eq(eduTeacher.getLevel() != null, EduTeacher::getLevel, eduTeacher.getLevel())
                .orderByDesc(EduTeacher::getLevel).orderByDesc(EduTeacher::getSort);
        eduTeacherService.page(eduTeacherPage, queryWrapper);
        List<EduTeacher> eduTeacherList = eduTeacherPage.getRecords();
        long total = eduTeacherPage.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("eduTeacherList", eduTeacherList);
        map.put("total", total);
        return Result.success(map);
    }

    @PostMapping("/save")
    public Result save(@RequestBody EduTeacher eduTeacher) {
        eduTeacherService.save(eduTeacher);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        //根据教师id查询其名下的所有课程id
        LambdaQueryWrapper<EduCourse> courseQueryWrapper = new LambdaQueryWrapper<>();
        courseQueryWrapper.eq(EduCourse::getTeacherId, id);
        List<EduCourse> eduCourseList = eduCourseService.list(courseQueryWrapper);
        HashSet<String> set = new HashSet<>();
        for (EduCourse eduCourse : eduCourseList) {
            set.add(eduCourse.getId());
        }
        //删除视频
        LambdaQueryWrapper<EduVideo> videoQueryWrapper = new LambdaQueryWrapper<>();
        videoQueryWrapper.in(EduVideo::getCourseId, id);
        eduVideoService.remove(videoQueryWrapper);
        //删除章节
        LambdaQueryWrapper<EduChapter> chapterQueryWrapper = new LambdaQueryWrapper<>();
        chapterQueryWrapper.in(EduChapter::getCourseId, set);
        eduChapterService.remove(chapterQueryWrapper);
        //删除课程
        eduCourseService.remove(courseQueryWrapper);
        //删除教师
        eduTeacherService.removeById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody EduTeacher eduTeacher) {
        LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduTeacher::getId, eduTeacher.getId());
        eduTeacherService.update(eduTeacher, queryWrapper);
        return Result.success();
    }
}

