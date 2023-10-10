package com.rjx.edu.mapper;

import com.rjx.edu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjx.edu.entity.frontVo.EduCourseInfoVo;
import com.rjx.edu.entity.vo.EduCoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public EduCoursePublishVo getEduCoursePublishVo(String courseId);

    public EduCourseInfoVo getEduCourseInfoVo(String courseId);
}
