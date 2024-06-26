package com.rjx.oss.controller;

import com.rjx.oss.service.OssService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/oss")
//@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping("/upload")
    public Result uploadOssFile(@RequestBody MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadAvatar(file);
        return Result.success(url);
    }

}
