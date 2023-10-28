package com.rjx.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjx.edu.entity.EduChapter;
import com.rjx.edu.entity.EduVideo;
import com.rjx.edu.entity.vo.EduChapterVo;
import com.rjx.edu.service.EduChapterService;
import com.rjx.edu.service.EduVideoService;
import com.rjx.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu/chapter")
//@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduVideoService eduVideoService;

    @GetMapping("/list/{courseId}")
    public Result getAll(@PathVariable String courseId) {
        List<EduChapterVo> eduChapterVoList = eduChapterService.getAll(courseId);
        return Result.success(eduChapterVoList);
    }

    @GetMapping("/{chapterId}")
    public Result getById(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return Result.success(eduChapter);
    }

    @PostMapping("/save")
    public Result save(@RequestBody EduChapter eduChapter) {
        EduChapter chapter = new EduChapter();
        BeanUtils.copyProperties(eduChapter, chapter);
        eduChapterService.save(chapter);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return Result.success();
    }

    @DeleteMapping("/deleteById/{chapterId}")
    public Result deleteById(@PathVariable String chapterId) {
        LambdaQueryWrapper<EduVideo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduVideo::getChapterId, chapterId);
        eduVideoService.remove(queryWrapper);
        eduChapterService.removeById(chapterId);
        return Result.success();
    }
}

