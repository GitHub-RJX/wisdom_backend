package com.rjx.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjx.cms.entity.CmsBanner;
import com.rjx.cms.service.CmsBannerService;
import com.rjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cms/bannerAdmin")
//@CrossOrigin
public class CmsBannerAdminController {
    @Autowired
    private CmsBannerService cmsBannerService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        CmsBanner cmsBanner = cmsBannerService.getById(id);
        return Result.success(cmsBanner);
    }

    @GetMapping("/page/{current}/{size}")
    public Result getByPage(@PathVariable Long current, @PathVariable Long size) {
        Page<CmsBanner> crmBannerPage = new Page<>(current, size);
        cmsBannerService.page(crmBannerPage);
        Map<String, Object> map = new HashMap<>();
        map.put("crmBannerList", crmBannerPage.getRecords());
        map.put("total", crmBannerPage.getTotal());
        return Result.success(map);
    }

    @PostMapping("/save")
    public Result save(@RequestBody CmsBanner cmsBanner) {
        cmsBannerService.save(cmsBanner);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody CmsBanner cmsBanner) {
        cmsBannerService.updateById(cmsBanner);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        cmsBannerService.removeById(id);
        return Result.success();
    }
}

