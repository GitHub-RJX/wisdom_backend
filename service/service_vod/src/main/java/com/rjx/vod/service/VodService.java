package com.rjx.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    //上传视频到阿里云
    String upload(MultipartFile file);

    //删除多个阿里云视频的方法
    void removeByIds(List videoIdList);
}
