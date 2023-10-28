package com.rjx.edu.controller;

import com.rjx.edu.client.VodClient;
import com.rjx.edu.entity.EduVideo;
import com.rjx.edu.service.EduVideoService;
import com.rjx.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu/video")
//@CrossOrigin
public class EduVideoController {
    @Autowired
    private VodClient vodClient;
    @Autowired
    private EduVideoService eduVideoService;

//    @GetMapping("/list")
//    public Result getAll() {
//        Result result = vodClient.getAll();
//        return Result.success(result.getData());
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public Result deleteById(@PathVariable String id) {
//        Result result = vodClient.removeById(id);
//        return Result.success(result.getData());
//    }
//
//    @DeleteMapping("/deleteList")
//    public Result deleteList(@RequestParam List<String> videoIdList) {
//        Result result = vodClient.removeByIds(videoIdList);
//        return Result.success(result.getData());
//    }

    @GetMapping("/{videoId}")
    public Result getById(@PathVariable String videoId) {
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return Result.success(eduVideo);
    }

    @DeleteMapping("/delete/{videoId}")
    public Result deleteById(@PathVariable String videoId) {
        eduVideoService.removeById(videoId);
        return Result.success();
    }

    @PostMapping("/save")
    public Result save(@RequestBody EduVideo eduVideo) {
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(eduVideo, video);
        eduVideoService.save(video);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return Result.success();
    }
}

