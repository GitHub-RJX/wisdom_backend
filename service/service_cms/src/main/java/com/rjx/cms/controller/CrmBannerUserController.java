package com.rjx.cms.controller;

import com.rjx.cms.entity.CrmBanner;
import com.rjx.cms.service.CrmBannerService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cms/bannerUser")
//@CrossOrigin
public class CrmBannerUserController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("/list")
    public Result getAll() {
        List<CrmBanner> crmBannerList = crmBannerService.getAll();
        return Result.success(crmBannerList);
    }
}

