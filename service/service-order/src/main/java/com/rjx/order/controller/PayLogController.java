package com.rjx.order.controller;

import com.rjx.order.service.PayLogService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order/paylog")
//@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    @GetMapping("/createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.createNative(orderNo);
        return Result.success(map);
    }

    @GetMapping("/getPayStatus/{orderNo}")
    public Result getPayStatus(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.getPayStatus(orderNo);
        if (map == null) {
            return Result.error("支付出错了！");
        }
        if (map.get("trade_status").equals("SUCCESS")) {
            payLogService.updateStatus(map);
            return Result.success("支付成功！");
        }
        return Result.success("支付中，请稍后...");
    }
}

