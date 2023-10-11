package com.rjx.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjx.cms.entity.CrmBanner;
import com.rjx.cms.mapper.CrmBannerMapper;
import com.rjx.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(key = "'crmBannerList'", value = "AAABBBCCC")
    public List<CrmBanner> getAll() {
        LambdaQueryWrapper<CrmBanner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(CrmBanner::getSort);
        queryWrapper.last("limit 2");
        List<CrmBanner> crmBannerList = baseMapper.selectList(queryWrapper);
        return crmBannerList;
    }
}
