package com.rjx.edu.entity.treeResult;

import lombok.Data;

import java.util.List;

@Data
public class OneEduSubject {
    private String id;
    private String title;
    private List<TwoEduSubject> children;
}
