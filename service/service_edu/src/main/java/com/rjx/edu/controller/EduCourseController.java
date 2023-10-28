package com.rjx.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.edu.entity.EduChapter;
import com.rjx.edu.entity.EduCourse;
import com.rjx.edu.entity.EduVideo;
import com.rjx.edu.entity.vo.EduCourseInfo;
import com.rjx.edu.entity.vo.EduCoursePublishVo;
import com.rjx.edu.entity.vo.EduCourseVo;
import com.rjx.edu.service.*;
import com.rjx.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edu/course")
//@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduSubjectService eduSubjectService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduVideoService eduVideoService;

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
        ArrayList<EduCourseVo> eduCourseVoList = new ArrayList<>();
        for (EduCourse course : eduCourseList) {
            EduCourseVo eduCourseVo = new EduCourseVo();
            BeanUtils.copyProperties(course, eduCourseVo);
            eduCourseVo.setTeacherName(eduTeacherService.getById(course.getTeacherId()).getName());
            eduCourseVo.setSubjectTitle(eduSubjectService.getById(course.getSubjectId()).getTitle());
            eduCourseVoList.add(eduCourseVo);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("eduCourseVoList", eduCourseVoList);
        map.put("total", total);
        return Result.success(map);
    }

    @PostMapping("/save")
    public Result save(@RequestBody EduCourseInfo eduCourseInfo) {
        if (eduCourseInfo.getTitle().length() == 0 || eduCourseInfo.getTeacherId().length() == 0 || eduCourseInfo.getSubjectParentId().length() == 0 || eduCourseInfo.getSubjectId().length() == 0) {
            return Result.error("缺少必要信息，请完善表单内容！");
        }
        String id = eduCourseService.saveInfo(eduCourseInfo);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return Result.success(map);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        eduCourseService.removeById(id);
        LambdaQueryWrapper<EduChapter> chapterQueryWrapper = new LambdaQueryWrapper<>();
        chapterQueryWrapper.eq(EduChapter::getCourseId, id);
        eduChapterService.remove(chapterQueryWrapper);
        LambdaQueryWrapper<EduVideo> videoQueryWrapper = new LambdaQueryWrapper<>();
        videoQueryWrapper.eq(EduVideo::getCourseId, id);
        eduVideoService.remove(videoQueryWrapper);
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

    @PutMapping("/updateDraft/{courseId}")
    public Result updateDraft(@PathVariable String courseId) {
        EduCourse eduCourse = eduCourseService.getById(courseId);
        eduCourse.setStatus("Draft");
        eduCourseService.updateById(eduCourse);
        return Result.success();
    }

    @PutMapping("/updateNormal/{courseId}")
    public Result updateNormal(@PathVariable String courseId) {
        EduCourse eduCourse = eduCourseService.getById(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.success();
    }
}

