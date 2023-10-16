package com.rjx.edu.controller;

import com.rjx.edu.client.VodClient;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu/video")
//@CrossOrigin
public class EduVideoController {
    @Autowired
    private VodClient vodClient;

//    @GetMapping("/list")
//    public Result getAll() {
//        Result result = producerClient.getAll();
//        return Result.success(result.getData());
//    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        Result result = vodClient.removeById(id);
        return Result.success(result.getData());
    }

    @DeleteMapping("/deleteList")
    public Result deleteList(@RequestParam List<String> videoIdList) {
        Result result = vodClient.removeByIds(videoIdList);
        return Result.success(result.getData());
    }
}

