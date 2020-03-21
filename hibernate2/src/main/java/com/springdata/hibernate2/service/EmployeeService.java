package com.springdata.hibernate2.service;

import com.springdata.hibernate2.entity.Employee;
import com.springdata.hibernate2.repository.Employeerepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private Employeerepos employeerepos;

    public void createEmployee()
    {
        employeerepos.save(new Employee("shruti","sharma",40000,46));
    }

    public void findNamesBySalary(){
        List<Object[]> employeeBySalary = employeerepos.findBySalary();
        for (Object[] objects : employeeBySalary){
            System.out.println(objects[0]+" "+objects[1]);
        }
    }

    public void updateSalary(){
        int avgSalary = employeerepos.averageSalary();
        employeerepos.updateSalary(avgSalary,10000);
        System.out.println(employeerepos.findAll());
    }

    public void deleteEmployee(){
        int minSalary = employeerepos.minimumSalary();
        employeerepos.deleteEmployee(minSalary);
        System.out.println(employeerepos.findAll());
    }


    public void getEmployeeSingh(){
        List<Object[]> employeeSingh = employeerepos.employeeSingh();
        for (Object[] o:employeeSingh){
            System.out.println(o[0]+" "+o[1]+" "+o[2]);
        }
    }


    public void deleteEmployeeGreater(){
        employeerepos.deleteEmployeeByGreaterAge();
    }
}
