package com.springdata.hibernate2;

import com.springdata.hibernate2.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Hibernate2Application {

	public static void main(String[] args)
	{
		ApplicationContext applicationContext=SpringApplication.run(Hibernate2Application.class, args);
		EmployeeService employeeService=applicationContext.getBean(EmployeeService.class);

//		employeeService.createEmployee();
		/*JPQL*/
//		System.out.println(employeerepos.findAllEmployee());

		/*Ques1 Display the first name, last name of all employees having salary greater than average salary ordered in
		 ascending by their age and in descending by their salary.*/
		employeeService.findNamesBySalary();

		/*Ques2 Update salary of all employees by a salary passed as a parameter whose existing salary is less
		than the average salary.*/
		employeeService.updateSalary();

		//Ques3 Delete all employees with minimum salary.
		employeeService.deleteEmployee();

		/*Native SQL Query:*/

		//Ques4: Display the id, first name, age of all employees where last name ends with "singh"
		employeeService.getEmployeeSingh();

		//Ques5: Delete all employees with age greater than 45(Should be passed as a parameter)
		employeeService.deleteEmployeeGreater();
	}
}
