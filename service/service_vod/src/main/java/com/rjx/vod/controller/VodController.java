package com.rjx.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.rjx.base.exceptionhandler.WisdomException;
import com.rjx.utils.Result;
import com.rjx.vod.Utils.ConstantVodUtils;
import com.rjx.vod.Utils.InitVodCilent;
import com.rjx.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vod")
//@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("upload")
    public Result upload(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.upload(file);
        return Result.success(videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeById/{id}")
    public Result removeById(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WisdomException(20001, "删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    @DeleteMapping("removeByIds")
    public Result removeByIds(@RequestParam List<String> videoIdList) {
        vodService.removeByIds(videoIdList);
        return Result.success();
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id) {
        try {
            //创建初始化对象
            DefaultAcsClient client =
                    InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(id);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return Result.success(playAuth);
        } catch (Exception e) {
            throw new WisdomException(20001, "获取凭证失败");
        }
    }
}
