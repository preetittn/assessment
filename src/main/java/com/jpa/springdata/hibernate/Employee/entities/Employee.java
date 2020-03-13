package com.jpa.springdata.hibernate.Employee.entities;

import javax.persistence.*;

@Entity               //Ques1. Create an Employee Entity which contains following fields:name,id,age,location
@Table(name = "employee")                     //if table name different in db then specify name.
public class Employee {
    private String name;
    @Id
    private int id;
    private int age;
    @Column(name="location")
    private String loc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
