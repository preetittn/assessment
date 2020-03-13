package com.jpa.springdata.hibernate;

import com.jpa.springdata.hibernate.Employee.Repository.Employeerepos;
import com.jpa.springdata.hibernate.Employee.entities.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HibernateApplicationTests {

	@Autowired
	Employeerepos employeerepos;

	@Test
	void contextLoads() {
	}

	//Ques3. Perform Create Operation on Entity using Spring Data JPA
	@Test
	public void testCreate() {
		Employee employee = new Employee();
		employee.setName("preeti");
		employee.setId(1);
		employee.setAge(26);
		employee.setLoc("mehrauli");

		Employee employee2 = new Employee();
		employee2.setName("gaurav");
		employee2.setId(2);
		employee2.setAge(23);
		employee2.setLoc("central delhi");

		Employee employee3 = new Employee();
		employee3.setName("dharmendra");
		employee3.setId(3);
		employee3.setAge(19);
		employee3.setLoc("bhajanpura");

		Employee employee4 = new Employee();
		employee4.setName("anukriti");
		employee4.setId(4);
		employee4.setAge(28);
		employee4.setLoc("badarpur");

		Employee employee5 = new Employee();
		employee5.setName("ambika");
		employee5.setId(5);
		employee5.setAge(24);
		employee5.setLoc("karkardooma");

		employeerepos.save(employee);
		employeerepos.save(employee2);
		employeerepos.save(employee3);
		employeerepos.save(employee4);
		employeerepos.save(employee5);


	}

	//Ques4. Perform update Operation on Entity using Spring Data JPA
	@Test
	public void testUpdate() {
		Optional<Employee> employee = employeerepos.findById(1);
		employee.get().setAge(20);
		employeerepos.save(employee.get());
	}


	//Ques5. Perform delete Operation on Entity using Spring Data JPA
	@Test
	public void testDelete() {
		if (employeerepos.existsById(4)) {
			employeerepos.deleteById(4);
		}
	}

	//Ques6. Perform read Operation on Entity using Spring Data JPA
	@Test
	public void testRead()
	{
		Optional<Employee> employee = employeerepos.findById(3);
		assertNotNull(employee);
		assertEquals("dharmendra", employee.get().getName());
		System.out.println("****************" + employee.get().getName());
	}

	//Ques7. Get the total count of the number of Employees
	@Test
	public void testCount()
	{
		System.out.println("*****************total records: "+employeerepos.count());
	}

	//Ques8. Implement Pagination and Sorting on the bases of Employee Age
	@Test
	public void testPagingandSorting()									//question8
	{
		Pageable pageable= PageRequest.of(0,5, Sort.Direction.ASC,"age");
		employeerepos.findAll(pageable).forEach(p -> System.out.println(p.getName()));
	}

	//Ques9. Create and use finder to find Employee by Name
	@Test
	public void testFindByName()
	{
		List<Employee >employeeList=employeerepos.findByName("ambika");
		employeeList.forEach(p -> System.out.println("*****"+p.getId()+" " +p.getAge()+" "+p.getName()+" "+p.getLoc()));
	}

	//Ques10. Create and use finder to find Employees starting with A character
	@Test
	public void testFindByNameLike()
	{
		List<Employee >employeeList=employeerepos.findByNameLike("a%");
		employeeList.forEach(p -> System.out.println("*******"+p.getId()+" " +p.getAge()+" "+p.getName()+" "+p.getLoc()));
	}

	//Ques11. Create and use finder to find Employees Between the age of 20 to 30
	@Test
	public void testFindByAgeBetween()
	{
		List<Employee >employeeList=employeerepos.findByAgeBetween(20,30);
		employeeList.forEach(p -> System.out.println("*********"+p.getId()+" " +p.getAge()+" "+p.getName()+" "+p.getLoc()));
	}
}
