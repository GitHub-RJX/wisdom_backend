package com.rjx.statistics.controller;

import com.rjx.statistics.service.StatisticsDailyService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/statistics/daily")
//@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @PostMapping("/countRegisters/{date}")
    public Result countRegisters(@PathVariable String date) {
        statisticsDailyService.countRegisters(date);
        return Result.success();
    }

    @GetMapping("/getShowData/{type}/{begin}/{end}")
    public Result getShowData(@PathVariable String type, @PathVariable String begin, @PathVariable String end) {
        Map<String, Object> map = statisticsDailyService.getShowData(type, begin, end);
        return Result.success(map);
    }
}

