package com.springdata.hibernate2.repository;

import com.springdata.hibernate2.entity.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface Employeerepos extends PagingAndSortingRepository<Employee,Integer> {

    @Query("from Employee")
    List<Employee> findAllEmployee();

    //Ques1:Display the first name, last name of all employees having salary greater than average salary
    // ordered in ascending by their age and in descending by their salary.
    @Query("select firstName,lastName from Employee where salary>(select avg(salary)"+
            "from Employee ORDER BY age ASC, salary DESC)")
    List<Object[]> findBySalary();

    //Ques2:Update salary of all employees by a salary passed as a parameter whose existing salary is less than the average salary.
    /*@Transactional
    @Modifying
    @Query("Update Employee emp set emp.salary=:salary " +
            "where emp.salary < (select avg(salary) from Employee)")
    List<Object[]> updateSalary(@Param("salary") int salary);*/

    @Query("SELECT avg(salary) from Employee")
    int averageSalary();

    @Transactional
    @Modifying
    @Query("UPDATE Employee emp set emp.salary=:sal where emp.salary<:salary1")
    void updateSalary(@Param("salary1") int salary1, @Param("sal") int sal);

    //Ques3:Delete all employees with minimum salary.
    @Query("SELECT min(salary) from Employee")
    int minimumSalary();
    @Transactional
    @Modifying
    @Query("DELETE from Employee emp where emp.salary=:minSalary")
    void deleteEmployee(@Param("minSalary") int minSalary);

    //Quse4:Display the id, first name, age of all employees where last name ends with "singh"
    @Query(value = "SELECT empId,empFirstName,empAge from employeeTable where empLastName like '%Singh'",
            nativeQuery = true)
    List<Object[]> employeeSingh();

    //Ques5:Delete all employees with age greater than 45(Should be passed as a parameter)
    @Transactional
    @Modifying
    @Query(value = "DELETE from employeeTable where empAge > 45",nativeQuery = true)
    void deleteEmployeeByGreaterAge();



}
