package com.rjx.edu.entity.frontVo;

import lombok.Data;

@Data
public class EduCourseVo {
    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
    private String buyCountSort;
    private String gmtCreateSort;
    private String priceSort;
}
