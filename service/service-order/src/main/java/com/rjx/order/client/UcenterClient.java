package com.rjx.order.client;

import com.rjx.ucenter.entity.Ucenter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {
    @GetMapping("/ucenter/getById/{id}")
    public Ucenter getById(@PathVariable String id);
}
