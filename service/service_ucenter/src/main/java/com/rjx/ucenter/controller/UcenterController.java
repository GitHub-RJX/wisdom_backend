package com.rjx.ucenter.controller;

import com.rjx.ucenter.entity.Ucenter;
import com.rjx.ucenter.entity.vo.RegisterVo;
import com.rjx.ucenter.service.UcenterService;
import com.rjx.utils.JwtUtils;
import com.rjx.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ucenter")
//@CrossOrigin
public class UcenterController {
    @Autowired
    private UcenterService ucenterService;

    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo) {
        ucenterService.register(registerVo);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody Ucenter ucenter) {
        String token = ucenterService.login(ucenter);
        return Result.success(token);
    }

    @GetMapping("/getByToken")
    public Result getByToken(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        Ucenter ucenter = ucenterService.getById(id);
        return Result.success(ucenter);
    }

    @GetMapping("/getById/{id}")
    public Ucenter getById(@PathVariable String id) {
        Ucenter ucenter = ucenterService.getById(id);
        return ucenter;
    }

    @GetMapping("/countRegisters/{date}")
    public Result countRegisters(@PathVariable String date) {
        Integer num = ucenterService.countRegisters(date);
        return Result.success(num);
    }
}

