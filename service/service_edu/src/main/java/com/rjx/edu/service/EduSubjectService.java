package com.rjx.edu.service;

import com.rjx.edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rjx.edu.entity.treeResult.OneEduSubject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EduSubjectService extends IService<EduSubject> {

    void saveEduSubject(MultipartFile multipartFile,EduSubjectService eduSubjectService);

    List<OneEduSubject> getAll();
}
