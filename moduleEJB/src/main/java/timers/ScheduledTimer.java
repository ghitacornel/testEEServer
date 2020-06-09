package timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import java.util.Date;

@Startup
@Singleton
public class ScheduledTimer {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTimer.class);

    @Schedule(minute = "*/1", hour = "*", info = "My Scheduled Timer", persistent = false)
    public void schedule(Timer timer) {
        logger.info(this + " schedule executed now and every minute " + new Date() + " using timer " + timer.getInfo());
    }

}
