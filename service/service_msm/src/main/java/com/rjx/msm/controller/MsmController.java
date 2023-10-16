package com.rjx.msm.controller;

import com.rjx.msm.service.Msmservice;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/msm")
//@CrossOrigin
public class MsmController {
    @Autowired
    private Msmservice msmservice;

    @GetMapping("/getCode/{phone}")
    public Result getCode(@PathVariable String phone) {
        Random random = new Random();
        int num = random.nextInt(9000) + 1000;
        String code = String.valueOf(num);
        System.out.println(num + "   " + code);
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        return Result.success(map);
//        Boolean isSend = msmservice.sendMsm(map, phone);
//        if (isSend) {
//            return Result.success(map);
//        } else {
//            return Result.error("信息发送失败，请稍后再试！");
//        }
    }
}
