package com.rjx.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjx.edu.entity.EduSubject;
import com.rjx.edu.entity.excelListener.EduSubjectListener;
import com.rjx.edu.entity.treeResult.OneEduSubject;
import com.rjx.edu.entity.treeResult.TwoEduSubject;
import com.rjx.edu.mapper.EduSubjectMapper;
import com.rjx.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveEduSubject(MultipartFile multipartFile, EduSubjectService eduSubjectService) {
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EasyExcel.read(inputStream, EduSubject.class, new EduSubjectListener()).sheet().doRead();
    }

    @Override
    public List<OneEduSubject> getAll() {
        LambdaQueryWrapper<EduSubject> wrapperOne = new LambdaQueryWrapper<>();
        wrapperOne.eq(EduSubject::getParentId, "0");
//        List<EduSubject> eduSubjectListOne = baseMapper.selectList(wrapperOne);
        List<EduSubject> eduSubjectListOne = this.list(wrapperOne);

        LambdaQueryWrapper<EduSubject> wrapperTwo = new LambdaQueryWrapper<>();
        wrapperTwo.ne(EduSubject::getParentId, "0");
//        List<EduSubject> eduSubjectListTwo = baseMapper.selectList(wrapperTwo);
        List<EduSubject> eduSubjectListTwo = this.list(wrapperTwo);


        List<OneEduSubject> eduSubjectList = new ArrayList<>();
        for (EduSubject eduSubjectOne : eduSubjectListOne) {
            OneEduSubject oneEduSubject = new OneEduSubject();
            BeanUtils.copyProperties(eduSubjectOne, oneEduSubject);

            List<TwoEduSubject> children = new ArrayList<>();
            for (EduSubject eduSubjectTwo : eduSubjectListTwo) {
                if (eduSubjectTwo.getParentId().equals(eduSubjectOne.getId())) {
                    TwoEduSubject twoEduSubject = new TwoEduSubject();
                    BeanUtils.copyProperties(eduSubjectTwo, twoEduSubject);
                    children.add(twoEduSubject);
                }
            }
            oneEduSubject.setChildren(children);

            eduSubjectList.add(oneEduSubject);
        }
        return eduSubjectList;
    }
}

