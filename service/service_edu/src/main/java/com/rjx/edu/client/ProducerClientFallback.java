package com.rjx.edu.client;

import com.rjx.utils.Result;
import org.springframework.stereotype.Component;

@Component
public class ProducerClientFallback implements ProducerClient {
    @Override
    public Result getAll() {
        return Result.error("查询所有视频的操作 出错啦！！");
    }

    @Override
    public Result deleteById(String id) {
        return Result.error("根据id值" + id + "视频的操作 出错啦！！");
    }
}
