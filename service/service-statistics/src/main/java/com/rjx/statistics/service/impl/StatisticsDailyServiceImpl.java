package com.rjx.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rjx.statistics.client.UcenterClient;
import com.rjx.statistics.entity.StatisticsDaily;
import com.rjx.statistics.mapper.StatisticsDailyMapper;
import com.rjx.statistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjx.utils.Result;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void countRegisters(String date) {
        Result result = ucenterClient.countRegisters(date);
        Integer num = (Integer) result.getData();
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(num);
        statisticsDaily.setDateCalculated(date);
        statisticsDaily.setLoginNum(RandomUtils.nextInt(1000));
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(1000));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(1000));
        LambdaQueryWrapper<StatisticsDaily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StatisticsDaily::getDateCalculated, date);
        baseMapper.delete(queryWrapper);
        baseMapper.insert(statisticsDaily);
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", begin, end);
        queryWrapper.select("date_calculated", type);
        List<StatisticsDaily> statisticsDailyList = baseMapper.selectList(queryWrapper);
        List<String> dateList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (StatisticsDaily statisticsDaily : statisticsDailyList) {
            dateList.add(statisticsDaily.getDateCalculated());
            switch (type) {
                case "register_num":
                    numList.add(statisticsDaily.getRegisterNum());
                    break;
                case "login_num":
                    numList.add(statisticsDaily.getLoginNum());
                    break;
                case "video_view_num":
                    numList.add(statisticsDaily.getVideoViewNum());
                    break;
                default:
                    numList.add(statisticsDaily.getCourseNum());
                    break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("dateList", dateList);
        map.put("numList", numList);
        return map;
    }
}
