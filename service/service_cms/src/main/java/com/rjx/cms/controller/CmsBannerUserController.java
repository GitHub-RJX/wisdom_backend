package com.rjx.cms.controller;

import com.rjx.cms.entity.CmsBanner;
import com.rjx.cms.service.CmsBannerService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cms/bannerUser")
//@CrossOrigin
public class CmsBannerUserController {
    @Autowired
    private CmsBannerService cmsBannerService;

    @GetMapping("/list")
    public Result getAll() {
        List<CmsBanner> cmsBannerList = cmsBannerService.getAll();
        return Result.success(cmsBannerList);
    }
}

