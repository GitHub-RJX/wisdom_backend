package com.rjx.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.edu.entity.EduTeacher;
import com.rjx.edu.service.EduTeacherService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edu/teacher")
//@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

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

    @GetMapping("/page/{current}/{size}")
    public Result getByPage(@PathVariable Long current, @PathVariable Long size, @RequestBody EduTeacher eduTeacher) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduTeacher::getIsDeleted, 0);
        queryWrapper.like(eduTeacher.getName() != null && eduTeacher.getName().length() != 0, EduTeacher::getName, eduTeacher.getName());
        queryWrapper.eq(eduTeacher.getLevel() != null, EduTeacher::getLevel, eduTeacher.getLevel());
        queryWrapper.orderByDesc(EduTeacher::getGmtCreate);
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
        LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduTeacher::getId, id);
        eduTeacherService.remove(queryWrapper);
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

