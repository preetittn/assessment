package springdata.hibernate2mappings.entities.JoinedTable;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class EmployeeJoined {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

