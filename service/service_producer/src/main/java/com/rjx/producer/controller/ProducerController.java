package com.rjx.producer.controller;

import com.rjx.utils.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
//@CrossOrigin
public class ProducerController {
    @GetMapping("/list")
    public Result getAll() {
        return Result.success("查询了所有视频");
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        return Result.success("根据id值 " + id + " 删除了视频");
    }

}
