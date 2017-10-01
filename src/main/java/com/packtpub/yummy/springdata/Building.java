package com.packtpub.yummy.springdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
public class Building {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String street;
    String zipCode;
    String city;
}
