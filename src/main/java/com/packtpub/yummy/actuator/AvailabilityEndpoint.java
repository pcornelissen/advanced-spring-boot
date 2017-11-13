package com.packtpub.yummy.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityEndpoint  extends AbstractEndpoint<Boolean>{
    private final ServiceAvailabilityStatus availabilityStatus;

    @Autowired
    public AvailabilityEndpoint(ServiceAvailabilityStatus availabilityStatus) {
        super("available",false);
        this.availabilityStatus = availabilityStatus;
    }

    public ServiceAvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    @Override
    public Boolean invoke() {
        return availabilityStatus.isAvailable();
    }
}
