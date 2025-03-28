package com.sb.auto.OrangeHRMSpringBootAutomation.framework.listener;

import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.testng.IExecutionListener;

import java.util.Date;

@Component
public class ReportPortalExecutionListener implements IExecutionListener {

    private static Launch launch;
    private static final Logger log = LogManager.getLogger(ReportPortalExecutionListener.class);

    @Override
    public void onExecutionStart() {
        long startTime= System.currentTimeMillis();
        log.info("Inform all the suite have started execution at: {}", startTime);
        if (launch == null) {
            launch = ReportPortal.builder().build().newLaunch(null);
            launch.start();
            log.info("✅ ReportPortal Launch started");
        }

    }

    @Override
    public void onExecutionFinish() {
        long endTime= System.currentTimeMillis();
        log.info("Inform all the suite have completed execution at: {}", endTime);
        if (launch != null) {
            FinishExecutionRQ finishExecutionRQ = new FinishExecutionRQ();
            finishExecutionRQ.setEndTime(new Date());
            launch.finish(finishExecutionRQ);
            log.info("✅ ReportPortal Launch finished");
        }
    }
}
