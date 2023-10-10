package com.rjx.edu.controller;

import com.rjx.edu.client.ProducerClient;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edu/video")
//@CrossOrigin
public class EduVideoController {
    @Autowired
    private ProducerClient producerClient;

    @GetMapping("/list")
    public Result getAll() {
        Result result = producerClient.getAll();
        return Result.success(result.getData());
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        Result result = producerClient.deleteById(id);
        return Result.success(result.getData());
    }
}

