package com.packtpub.yummy.actuator;

import org.springframework.boot.actuate.endpoint.mvc.AbstractEndpointMvcAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class AvailabilityEndpointController extends AbstractEndpointMvcAdapter<AvailabilityEndpoint> {
    private final AvailabilityEndpoint delegate;

    public AvailabilityEndpointController(AvailabilityEndpoint delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @ActuatorGetMapping
    @ResponseBody
    public ResponseEntity<?> get() {
        if (!delegate.isEnabled()) {
            return getDisabledResponse();
        }
        return ResponseEntity.ok(new Availability(delegate.getAvailabilityStatus().isAvailable()));
    }

    @ActuatorPutMapping
    @ResponseBody
    public ResponseEntity<?> put(@RequestBody Availability availability) {
        if (!delegate.isEnabled()) {
            return getDisabledResponse();
        }
        delegate.getAvailabilityStatus().setAvailable(availability.isAvailable());
        return ResponseEntity.ok(new Availability(delegate.getAvailabilityStatus().isAvailable()));
    }
}
