package com.rjx.edu.client;

import com.rjx.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class) //调用的服务名称
@Component
public interface VodClient {

    @DeleteMapping("/video/removeById/{id}")
    public Result removeById(@PathVariable String id);

    @DeleteMapping("/video/removeByIds")
    public Result removeByIds(@RequestParam List<String> videoIdList);
}
