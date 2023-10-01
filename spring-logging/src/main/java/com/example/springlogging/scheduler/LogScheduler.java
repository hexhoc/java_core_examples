package com.example.springlogging.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogScheduler {

    @Scheduled(fixedRate = 100)
    public void postTrace() {
        log.trace("Logging at TRACE level");
    }

    @Scheduled(fixedRate = 200)
    public void postDebug() {
        log.debug("Logging at DEBUG level");
    }

    @Scheduled(fixedRate = 300)
    public void postInfo() {
        log.info("Logging at INFO level");
    }

    @Scheduled(fixedRate = 500)
    public void postWarn() {
        log.warn("Logging at WARN level");
    }

    @Scheduled(fixedRate = 1000)
    public void postError() {
        log.error("Logging at ERROR level");
    }

}
