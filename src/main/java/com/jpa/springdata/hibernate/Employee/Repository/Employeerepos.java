package com.jpa.springdata.hibernate.Employee.Repository;

import com.jpa.springdata.hibernate.Employee.entities.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//Ques2.  Set up EmployeeRepository with Spring Data JPA
public interface Employeerepos extends PagingAndSortingRepository<Employee,Integer> {

    List<Employee> findByName(String name);
    List<Employee> findByNameLike(String name);
    List<Employee> findByAgeBetween(int age1,int age2);




}
