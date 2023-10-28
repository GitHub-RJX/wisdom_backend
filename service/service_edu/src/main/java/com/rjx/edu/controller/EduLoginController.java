package com.rjx.edu.controller;

import com.rjx.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/edu")
public class EduLoginController {

    @PostMapping("/login")
    public Result login() {
        Map<String, String> map = new HashMap<>();
        map.put("token", "YAGDHFEQAWFUHQAUHDSDRG");
        return Result.success(map);
    }

    @GetMapping("/info")
    public Result getInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("roles", "admin");
        map.put("name", "GGBoy");
        map.put("avatar", "https://tse2-mm.cn.bing.net/th/id/OIP-C.gbd--YpMKzfojAYUyeaIFAAAAA?pid=ImgDet&rs=1");
        return Result.success(map);
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.success();
    }
}
