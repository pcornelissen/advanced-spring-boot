package com.packtpub.yummy.rest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("test")
public class JsonTestController {


    @RequestMapping("customer")
    public Customer giveMeACustomer() {
        return new Customer("No one", 12, "here street", "54321", "test city", true);
    }

    @RequestMapping("employee")
    public Employee giveMeAnEmployee() {
        return new Employee("No one", 12, true);
    }

    @RequestMapping("contact")
    public Contact giveMeAContact() {
        return new Contact("No one", 12, "here street", "54321", "test city", 1200000, LocalDateTime.now().minusDays(100));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer {
        private String name;
        private int age;
        private String street;
        private String postalCode;
        private String city;
        private boolean active;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Employee {
        private String name;
        private int age;
        private boolean employed;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Contact {
        private String name;
        private int age;
        private String street;
        private String postalCode;
        private String city;
        private int expectedRevenue;
        private LocalDateTime lastContact;
    }

}
