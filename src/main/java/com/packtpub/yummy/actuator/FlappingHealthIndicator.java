package com.packtpub.yummy.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FlappingHealthIndicator extends AbstractHealthIndicator {
    /**
     * Actual actuator check logic.
     *
     * @param builder the {@link Builder} to report actuator status and details
     * @throws Exception any {@link Exception} that should create a {@link Status#DOWN}
     *                   system status.
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int seconds = LocalDateTime.now().getSecond() % 30;
        builder.withDetail("seconds", seconds);
        if (seconds < 5)
            builder.status(Status.UP);
        else if (seconds < 10)
            builder.down();
        else if (seconds < 15)
            builder.down(new RuntimeException("Bad Day"));
        else if (seconds < 20)
            builder.outOfService();
        else if (seconds < 25)
            builder.status("Custom");
        else {
            builder.unknown();
            builder.withException(new RuntimeException("Bad Mood"));
        }
    }
}
