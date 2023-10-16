package com.rjx.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjx.cms.entity.CmsBanner;
import com.rjx.cms.mapper.CmsBannerMapper;
import com.rjx.cms.service.CmsBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerMapper, CmsBanner> implements CmsBannerService {

    @Override
    @Cacheable(key = "'crmBannerList'", value = "AAABBBCCC")
    public List<CmsBanner> getAll() {
        LambdaQueryWrapper<CmsBanner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(CmsBanner::getSort);
        queryWrapper.last("limit 2");
        List<CmsBanner> cmsBannerList = baseMapper.selectList(queryWrapper);
        return cmsBannerList;
    }
}
