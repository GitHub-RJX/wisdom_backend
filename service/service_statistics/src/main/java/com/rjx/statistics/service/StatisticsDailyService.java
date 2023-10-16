package com.rjx.statistics.service;

import com.rjx.statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author rjx
 * @since 2023-10-07
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void countRegisters(String date);

    Map<String, Object> getShowData(String type, String begin, String end);
}
