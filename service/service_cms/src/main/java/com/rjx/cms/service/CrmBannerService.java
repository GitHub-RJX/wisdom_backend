package com.rjx.cms.service;

import com.rjx.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getAll();
}
