package com.rjx.edu.entity.excelListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjx.edu.entity.EduSubject;
import com.rjx.edu.entity.excelData.EduSubjectData;
import com.rjx.edu.service.EduSubjectService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EduSubjectListener extends AnalysisEventListener<EduSubjectData> {
    private EduSubjectService eduSubjectService;

    @Override
    public void invoke(EduSubjectData eduSubjectData, AnalysisContext analysisContext) {
        if (eduSubjectData == null) {
            try {
                throw new Exception("文件数据为空！");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        EduSubject eduSubjectOne = this.exitOneName(eduSubjectService, eduSubjectData.getOneName());
        if (eduSubjectOne == null) {
            eduSubjectOne = new EduSubject();
            eduSubjectOne.setParentId("0");
            eduSubjectOne.setTitle(eduSubjectData.getOneName());
            eduSubjectService.save(eduSubjectOne);
        }
        String pid = eduSubjectOne.getId();
        EduSubject eduSubjectTwo = this.exitTwoName(eduSubjectService, eduSubjectData.getTwoName(), pid);
        if (eduSubjectTwo == null) {
            eduSubjectTwo = new EduSubject();
            eduSubjectTwo.setParentId(pid);
            eduSubjectTwo.setTitle(eduSubjectData.getTwoName());
            eduSubjectService.save(eduSubjectTwo);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private EduSubject exitOneName(EduSubjectService eduSubjectService, String name) {
        LambdaQueryWrapper<EduSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduSubject::getTitle, name);
        wrapper.eq(EduSubject::getParentId, "0");
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    private EduSubject exitTwoName(EduSubjectService eduSubjectService, String name, String pid) {
        LambdaQueryWrapper<EduSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduSubject::getTitle, name);
        wrapper.eq(EduSubject::getParentId, pid);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }
}
