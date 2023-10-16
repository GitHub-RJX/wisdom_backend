package com.rjx.cms.service;

import com.rjx.cms.entity.CmsBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CmsBannerService extends IService<CmsBanner> {

    List<CmsBanner> getAll();
}
