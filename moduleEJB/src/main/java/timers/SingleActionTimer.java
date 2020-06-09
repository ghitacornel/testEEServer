package timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Date;

@Startup
@Singleton
public class SingleActionTimer {

    private static final Logger logger = LoggerFactory.getLogger(SingleActionTimer.class);

    @Resource
    TimerService timerService;

    @PostConstruct
    private void init() {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(this.getClass().getCanonicalName() + " Info");
        timerConfig.setPersistent(false);

        // create with a timeout of 5 seconds
        timerService.createSingleActionTimer(5000, timerConfig);
        logger.info("single action timer scheduler created at " + new Date());
    }

    @Timeout
    public void execute(Timer timer) {
        logger.info("Timer Service : " + timer.getInfo());
        execute();
    }

    public void execute() {
        logger.info(this + " executed now " + new Date());
    }

}
