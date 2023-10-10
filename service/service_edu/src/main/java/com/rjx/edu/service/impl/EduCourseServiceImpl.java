package com.rjx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.edu.entity.EduCourse;
import com.rjx.edu.entity.EduCourseDescription;
import com.rjx.edu.entity.frontVo.EduCourseInfoVo;
import com.rjx.edu.entity.frontVo.EduCourseVo;
import com.rjx.edu.entity.vo.EduCourseInfo;
import com.rjx.edu.entity.vo.EduCoursePublishVo;
import com.rjx.edu.mapper.EduCourseMapper;
import com.rjx.edu.service.EduCourseDescriptionService;
import com.rjx.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveInfo(EduCourseInfo eduCourseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourseInfo, eduCourse);
        baseMapper.insert(eduCourse);
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(eduCourseInfo.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public EduCourseInfo getInfoById(String id) {
        EduCourse eduCourse = this.getById(id);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        EduCourseInfo eduCourseInfo = new EduCourseInfo();
        BeanUtils.copyProperties(eduCourse, eduCourseInfo);
        eduCourseInfo.setDescription(eduCourseDescription.getDescription());
        return eduCourseInfo;
    }

    @Override
    public void updateInfo(EduCourseInfo eduCourseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourseInfo, eduCourse);
        this.updateById(eduCourse);
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourseInfo.getId());
        eduCourseDescription.setDescription(eduCourseInfo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public EduCoursePublishVo getEduCoursePublishVo(String courseId) {
        EduCoursePublishVo eduCoursePublishVo = baseMapper.getEduCoursePublishVo(courseId);
        return eduCoursePublishVo;
    }

    @Override
    public Map<String, Object> getByPage(Page<EduCourse> eduCoursePage, EduCourseVo eduCourseVo) {
        LambdaQueryWrapper<EduCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EduCourse::getIsDeleted, 0);
        if (!StringUtils.isEmpty(eduCourseVo.getSubjectParentId())) {
            queryWrapper.eq(EduCourse::getSubjectParentId, eduCourseVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(eduCourseVo.getSubjectId())) {
            queryWrapper.eq(EduCourse::getSubjectId, eduCourseVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(eduCourseVo.getBuyCountSort())) {
            queryWrapper.orderByDesc(EduCourse::getBuyCount);
        }
        if (!StringUtils.isEmpty(eduCourseVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc(EduCourse::getGmtCreate);
        }
        if (!StringUtils.isEmpty(eduCourseVo.getPriceSort())) {
            queryWrapper.orderByDesc(EduCourse::getPrice);
        }
        baseMapper.selectPage(eduCoursePage, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        List<EduCourse> eduCourseList = eduCoursePage.getRecords();
        long current1 = eduCoursePage.getCurrent();
        long pages = eduCoursePage.getPages();
        long size1 = eduCoursePage.getSize();
        long total = eduCoursePage.getTotal();
        boolean hasNext = eduCoursePage.hasNext();
        boolean hasPrevious = eduCoursePage.hasPrevious();
        map.put("eduCourseList", eduCourseList);
        map.put("current", current1);
        map.put("pages", pages);
        map.put("size", size1);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public EduCourseInfoVo getCourseInfoById(String id) {
        EduCourseInfoVo eduCourseInfoVo = baseMapper.getEduCourseInfoVo(id);
        return eduCourseInfoVo;
    }
}
