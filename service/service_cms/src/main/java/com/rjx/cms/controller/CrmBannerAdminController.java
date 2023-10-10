package com.rjx.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.cms.entity.CrmBanner;
import com.rjx.cms.service.CrmBannerService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cms/bannerAdmin")
//@CrossOrigin
public class CrmBannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        CrmBanner crmBanner = crmBannerService.getById(id);
        return Result.success(crmBanner);
    }

    @GetMapping("/page/{current}/{size}")
    public Result getByPage(@PathVariable Long current, @PathVariable Long size) {
        Page<CrmBanner> crmBannerPage = new Page<>(current, size);
        crmBannerService.page(crmBannerPage);
        Map<String, Object> map = new HashMap<>();
        map.put("crmBannerList", crmBannerPage.getRecords());
        map.put("total", crmBannerPage.getTotal());
        return Result.success(map);
    }

    @PostMapping("/save")
    public Result save(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        crmBannerService.removeById(id);
        return Result.success();
    }
}

