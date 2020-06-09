package timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Date;

@Startup
@Singleton
public class CalendarTimer {

    private static final Logger logger = LoggerFactory.getLogger(CalendarTimer.class);

    @Resource
    TimerService timerService;

    private int count = 0;

    @PostConstruct
    private void init() {

        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(this.getClass().getCanonicalName() + " Info");
        timerConfig.setPersistent(false);
        ScheduleExpression schedule = new ScheduleExpression();
        schedule.hour("*").minute("*").second("13,34,57");
        timerService.createCalendarTimer(schedule, timerConfig);

        logger.info("calendar action timer created at " + new Date());
    }

    @Timeout
    public void execute(Timer timer) {
        count++;
        logger.info("Timer Service : " + timer.getInfo());
        logger.info("Execution Time : " + new Date());
        logger.info(this + " executed times " + count);

        // cancel it after 6 times and see that it is not triggered anymore
        if (count > 6) timer.cancel();

    }

}
