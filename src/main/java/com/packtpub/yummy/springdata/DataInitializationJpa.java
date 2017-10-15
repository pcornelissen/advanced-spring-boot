package com.packtpub.yummy.springdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class DataInitializationJpa implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(DataInitializationJpa.class);

    @Autowired
    EmployeeRepository repo;
    @Autowired
    EntityManager em;

    private static volatile boolean initialized = false;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //It may happen that this is executed more than once
        if (initialized) return;
        initialized = true;

        Building headquarter = new Building(null, "World Headquarter", "BigStreet 23", "53117", "Bonn");
        Building subsidiary = new Building(null, "Just a small flat", "SmallStreet 2", "12345", "Berlin");

        em.persist(headquarter);
        em.persist(subsidiary);
        Employee e1 = new Employee(null, "Peter Parker", "Manhatten", 2000, headquarter);
        em.persist(e1);
        Employee e2 = new Employee(null, "John Wayne", "Wild West", 1000, headquarter);
        em.persist(e2);
        Employee e3 = new Employee(null, "Oliver S.", "Berlin", 10000, subsidiary);
        em.persist(e3);

    }

}
