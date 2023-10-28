package com.rjx.edu.controller;

import com.rjx.edu.entity.treeResult.OneEduSubject;
import com.rjx.edu.service.EduSubjectService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/edu/subject")
//@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/save")
    public Result save(MultipartFile multipartFile){
        eduSubjectService.saveEduSubject(multipartFile, eduSubjectService);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAll() {
        List<OneEduSubject> eduSubjectList = eduSubjectService.getAll();
        return Result.success(eduSubjectList);
    }
}

