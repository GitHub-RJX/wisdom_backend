package com.rjx.statistics.schedule;

import com.rjx.statistics.service.StatisticsDailyService;
import com.rjx.statistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {
    @Autowired
    StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        statisticsDailyService.countRegisters(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
