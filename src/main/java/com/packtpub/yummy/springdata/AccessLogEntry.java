package com.packtpub.yummy.springdata;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Data @NoArgsConstructor
public class AccessLogEntry {
    private String url;
    private LocalDateTime timestamp;

    public AccessLogEntry(WebRequest request) {
        url=request.getDescription(false);
        timestamp = LocalDateTime.now();
    }
}
