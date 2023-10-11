package com.rjx.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.edu.client.OrderClient;
import com.rjx.edu.entity.EduCourse;
import com.rjx.edu.entity.frontVo.EduCourseInfoVo;
import com.rjx.edu.entity.frontVo.EduCourseVo;
import com.rjx.edu.entity.vo.EduChapterVo;
import com.rjx.edu.service.EduChapterService;
import com.rjx.edu.service.EduCourseService;
import com.rjx.utils.JwtUtils;
import com.rjx.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edu/front/course")
//@CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private OrderClient orderClient;

    @PostMapping("/getByPage/{current}/{size}")
    public Result getByPage(@PathVariable Long current, @PathVariable Long size, @RequestBody EduCourseVo eduCourseVo) {
        Page<EduCourse> eduCoursePage = new Page<>(current, size);
        Map<String, Object> map = eduCourseService.getByPage(eduCoursePage, eduCourseVo);
        return Result.success(map);
    }

    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) {
        EduCourseInfoVo eduCourseInfoVo = eduCourseService.getCourseInfoById(id);
        List<EduChapterVo> eduChapterVoList = eduChapterService.getAll(id);
        boolean ifPay = orderClient.ifPayCourse(id, JwtUtils.getMemberIdByJwtToken(request));
        Map<String, Object> map = new HashMap<>();
        map.put("eduCourseInfoVo", eduCourseInfoVo);
        map.put("eduChapterVoList", eduChapterVoList);
        map.put("ifPay", ifPay);
        return Result.success(map);
    }

    @GetMapping("/getCourseInfoVoById/{id}")
    public EduCourseInfoVo getCourseInfoVoById(@PathVariable String id) {
        EduCourseInfoVo eduCourseInfoVo = eduCourseService.getCourseInfoById(id);
        return eduCourseInfoVo;
    }
}
