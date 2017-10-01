package com.packtpub.yummy.springdata;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

     Long countDistinctByNameAndIncome(String name, long income);
     List<Employee> removeByIncomeGreaterThan(long income);
     List<Employee> findEmployeesByNameIsLikeOrderByIncome(String name);
     List<Employee> findEmployeesByNameOrIncome(String name, long income);

}
