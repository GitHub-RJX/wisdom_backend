package com.rjx.edu.controller;


import com.rjx.edu.entity.vo.EduChapterVo;
import com.rjx.edu.service.EduChapterService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu/chapter")
//@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("/list/{courseId}")
    public Result getAll(@PathVariable String courseId) {
        List<EduChapterVo> eduChapterVoList = eduChapterService.getAll(courseId);
        return Result.success(eduChapterVoList);
    }

}

