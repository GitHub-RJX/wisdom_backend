package com.rjx.edu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EduChapterVo {

    private String id;
    private String title;
    private List<EduVideoVo> children = new ArrayList<>();
}
