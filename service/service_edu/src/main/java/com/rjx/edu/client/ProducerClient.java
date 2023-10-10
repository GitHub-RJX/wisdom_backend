package com.rjx.edu.client;

import com.rjx.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-producer", fallback = ProducerClientFallback.class)
public interface ProducerClient {
    @GetMapping("/producer/list")
    public Result getAll();

    @DeleteMapping("/producer/delete/{id}")
    public Result deleteById(@PathVariable String id);
}
