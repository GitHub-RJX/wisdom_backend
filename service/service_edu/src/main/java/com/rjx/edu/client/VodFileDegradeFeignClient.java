package com.rjx.edu.client;

import com.rjx.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient {
    //出错之后会执行
    @Override
    public Result removeById(String id) {
        return Result.error("删除视频出错了");
    }

    @Override
    public Result removeByIds(List<String> videoIdList) {
        return Result.error("删除多个视频出错了");
    }
}
