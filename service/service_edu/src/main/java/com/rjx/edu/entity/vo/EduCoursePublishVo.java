package com.rjx.edu.entity.vo;

import lombok.Data;

@Data
public class EduCoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
