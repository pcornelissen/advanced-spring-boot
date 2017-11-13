package com.packtpub.yummy.actuator;

import org.springframework.boot.actuate.endpoint.mvc.ActuatorMediaTypes;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.PUT, consumes = {
        ActuatorMediaTypes.APPLICATION_ACTUATOR_V1_JSON_VALUE,
        MediaType.APPLICATION_JSON_VALUE}, produces = {
        ActuatorMediaTypes.APPLICATION_ACTUATOR_V1_JSON_VALUE,
        MediaType.APPLICATION_JSON_VALUE})
@interface ActuatorPutMapping {

    /**
     * Alias for {@link RequestMapping#value}.
     *
     * @return the value
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

}
