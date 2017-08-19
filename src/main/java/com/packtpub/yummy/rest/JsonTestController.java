package com.packtpub.yummy.rest;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("test")
public class JsonTestController {


    @RequestMapping("customer")
    public Customer giveMeACustomer() {
        return new Customer(new Person("No one", 12), new Address("here street", "54321", "test city"), true);
    }

    @RequestMapping("employee")
    public Employee giveMeAnEmployee() {
        return new Employee(new Person("No one", 12), true);
    }

    @RequestMapping("employee2")
    @JsonView(SecretView.class)
    public Employee giveMeAnEmployeeSecret() {
        return new Employee(new Person("No one", 12), true);
    }

    @RequestMapping("contact")
    public Contact giveMeAContact() {
        return new Contact(new Person("No one", 12), new Address("here street", "54321", "test city"), 1200000, LocalDateTime.now().minusDays(100));
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        private String name;
        @JsonView(SecretView2.class)
        private int age;
    }
@Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private String street;
        private String postalCode;
        private String city;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer {
        @JsonUnwrapped
        private Person person;
        @JsonUnwrapped
        private Address address;
        private boolean active;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Employee {
        @JsonUnwrapped
        private Person person;
        @JsonView(SecretView.class)
        private boolean employed;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Contact {
        @JsonUnwrapped
        private Person person;
        @JsonUnwrapped
        private Address address;
        @JsonSerialize(using = MySerializer.class)
        private int expectedRevenue;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd. MMM YYYY hh:mm:ss")
        private LocalDateTime lastContact;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd. MMM YYYY hh:mm:ss", locale = "DE")
        public LocalDateTime getDate2(){
            return lastContact;
        }
    }

    private static class SecretView{}
    private static class SecretView2{}

    private static class MySerializer extends JsonSerializer<Integer> {
        /**
         * Method that can be called to ask implementation to serialize
         * values of type this serializer handles.
         *
         * @param value       Value to serialize; can <b>not</b> be null.
         * @param gen         Generator used to output resulting Json content
         * @param serializers Provider that can be used to get serializers for
         */
        @Override
        public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(value.toString()+"€");
        }
    }
}
