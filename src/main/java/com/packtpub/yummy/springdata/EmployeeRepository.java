package com.packtpub.yummy.springdata;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    Long countDistinctByNameAndIncome(@Param("name")String name, @Param("income") long income);

    Page<Employee> removeByIncomeGreaterThan(@Param("income") long income, Pageable var1);

    List<Employee> findEmployeesByNameIsLikeOrderByIncome(@Param("name") String name);

    List<Employee> findEmployeesByNameOrIncome(@Param("name") String name, @Param("income") Long income);

}
