package com.packtpub.yummy.actuator;

import lombok.Data;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TheTimeContributor implements InfoContributor {
    /**
     * Contributes additional details using the specified {@link Info.Builder Builder}.
     *
     * @param builder the builder to use
     */
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("the.time", LocalDateTime.now());
        builder.withDetail("the", new The());
    }
    @Data
    static class The{
        LocalDateTime time = LocalDateTime.now();
    }
}
