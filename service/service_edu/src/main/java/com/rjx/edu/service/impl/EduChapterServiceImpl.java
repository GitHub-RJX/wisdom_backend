package com.rjx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjx.edu.entity.EduChapter;
import com.rjx.edu.entity.EduVideo;
import com.rjx.edu.entity.vo.EduChapterVo;
import com.rjx.edu.entity.vo.EduVideoVo;
import com.rjx.edu.mapper.EduChapterMapper;
import com.rjx.edu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjx.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<EduChapterVo> getAll(String courseId) {
        LambdaQueryWrapper<EduChapter> queryWrapperC = new LambdaQueryWrapper<>();
        queryWrapperC.eq(EduChapter::getCourseId, courseId);
        queryWrapperC.orderByAsc(EduChapter::getSort);
        List<EduChapter> eduChapterList = this.list(queryWrapperC);

        LambdaQueryWrapper<EduVideo> queryWrapperV = new LambdaQueryWrapper<>();
        queryWrapperV.eq(EduVideo::getCourseId, courseId);
        queryWrapperV.orderByAsc(EduVideo::getSort);
        List<EduVideo> eduVideoList = eduVideoService.list(queryWrapperV);

        List<EduChapterVo> eduChapterVoList = new ArrayList<>();
        for (EduChapter chapter : eduChapterList) {
            EduChapterVo eduChapterVo = new EduChapterVo();
            BeanUtils.copyProperties(chapter, eduChapterVo);

            List<EduVideoVo> children = new ArrayList<>();
            for (EduVideo video : eduVideoList) {
                if (video.getChapterId().equals(chapter.getId())) {
                    EduVideoVo eduVideoVo = new EduVideoVo();
                    BeanUtils.copyProperties(video, eduVideoVo);
                    children.add(eduVideoVo);
                }
            }
            eduChapterVo.setChildren(children);

            eduChapterVoList.add(eduChapterVo);
        }
        return eduChapterVoList;
    }
}
