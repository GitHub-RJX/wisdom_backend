package com.rjx.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    //上传头像到oss
    String uploadAvatar(MultipartFile file);
}
