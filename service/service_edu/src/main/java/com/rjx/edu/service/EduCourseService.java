package com.rjx.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rjx.edu.entity.frontVo.EduCourseInfoVo;
import com.rjx.edu.entity.frontVo.EduCourseVo;
import com.rjx.edu.entity.vo.EduCourseInfo;
import com.rjx.edu.entity.vo.EduCoursePublishVo;

import java.util.Map;

public interface EduCourseService extends IService<EduCourse> {

    String saveInfo(EduCourseInfo eduCourseInfo);

    EduCourseInfo getInfoById(String id);

    void updateInfo(EduCourseInfo eduCourseInfo);

    EduCoursePublishVo getEduCoursePublishVo(String courseId);

    Map<String, Object> getByPage(Page<EduCourse> eduCoursePage, EduCourseVo eduCourseVo);

    EduCourseInfoVo getCourseInfoById(String id);
}
