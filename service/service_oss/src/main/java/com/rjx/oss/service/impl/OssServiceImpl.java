package com.rjx.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.rjx.oss.service.OssService;
import com.rjx.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    //上传头像到oss
    @Override
    public String uploadAvatar(MultipartFile file) {
        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();

            //获取原始文件名称
            String originalFilename = file.getOriginalFilename();
            //获取原始文件拓展名
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString();
            //拼接 fdgvzdsrfgvbgxdzgfvxdg01.jpg
            String filename = uuid + extension;
            //2 把文件按照日期进行分类
            //获取当前日期 2023/10/12
//            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接 2023/10/12/fdgvzdsrfgvbgxdzgfvxdg01.jpg
//            String fileName = datePath + "/" + filename;
            //3 把文件按照项目进行分类
            //拼接 wisdom/2023/10/12/fdgvzdsrfgvbgxdzgfvxdg01.jpg
            String fileName = "wisdom/" + filename;

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //  https://rjx-projects.oss-cn-beijing.aliyuncs.com/wisdom/1.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
