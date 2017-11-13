package com.packtpub.yummy.actuator;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ServiceAvailabilityStatus {
    private AtomicBoolean available = new AtomicBoolean(true);
    public boolean isAvailable(){
        return available.get();
    }
    public void setAvailable(boolean newStatus){
        available.set(newStatus);
    }
}

