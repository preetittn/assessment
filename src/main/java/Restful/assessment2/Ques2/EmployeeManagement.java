package Restful.assessment2.Ques2;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class EmployeeManagement{

    private static List<Employee> emp = new ArrayList<>();
    private static int userCount=3;
    static {
        emp.add(new Employee(1, "abc", 22));
        emp.add(new Employee(2, "xyz", 25));
        emp.add(new Employee(3, "pqr", 14));

    }


    //get all the user as described in que3
    public List<Employee> getAll() {
        return emp;
    }

    //get one employee using path variable
    public Employee findOne(int id) {
        for (Employee empl : emp) {
            if (empl.getId() == id) {
                return empl;
            }

        }
        return null;
    }


    //adding a employee
    public Employee addEmployee(Employee empl)
    {
        if(empl.getId() == null){
            empl.setId(userCount++);
        }
        emp.add(empl);
        return empl;
    }


    //delete
    public Employee deleteEmp(int id) {
        Iterator<Employee> employeeIterator = emp.iterator();
        {
            while (employeeIterator.hasNext()) {
                Employee emp = employeeIterator.next();
                if (emp.getId() == id) {
                    employeeIterator.remove();
                    return emp;
                }

            }
            return null;
        }
    }
    //put
    public Employee findId(Integer id)
    {
        Iterator<Employee> iterator=emp.iterator();
        while(iterator.hasNext()){
            Employee employee=iterator.next();
            if (employee.getId()==id)
                return employee;
        }
        return null;
    }
}

