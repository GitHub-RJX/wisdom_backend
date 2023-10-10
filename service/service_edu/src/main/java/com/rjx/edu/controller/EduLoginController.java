package com.rjx.edu.controller;

import com.rjx.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/edu/course")
//@CrossOrigin
public class EduLoginController {
    @PostMapping("/login")
    public Result login() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", "GGBoy");
        return Result.success(map);
    }

    @GetMapping("/info")
    public Result getInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "GGBoy");
        map.put("avatar", "https://tse2-mm.cn.bing.net/th/id/OIP-C.gbd--YpMKzfojAYUyeaIFAAAAA?pid=ImgDet&rs=1");
        return Result.success(map);
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.success();
    }
}
