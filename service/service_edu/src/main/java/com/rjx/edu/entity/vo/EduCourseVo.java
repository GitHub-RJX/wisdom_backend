package com.rjx.edu.entity.vo;

import com.rjx.edu.entity.EduCourse;
import lombok.Data;

@Data
public class EduCourseVo extends EduCourse {
    private String teacherName;
    private String subjectTitle;
}
