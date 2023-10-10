package com.rjx.edu.service;

import com.rjx.edu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rjx.edu.entity.vo.EduChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author rjx
 * @since 2023-09-11
 */
public interface EduChapterService extends IService<EduChapter> {

    List<EduChapterVo> getAll(String courseId);
}
