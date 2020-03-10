package com.example.restfulservices2.restfulservices2.model;

public class DetailedUser {
    private String name;
    private Integer age;
    private String add;
    private String phone;
    private Integer id;

    public DetailedUser(String name, Integer age, String add, String phone, Integer id) {
        this.name = name;
        this.age = age;
        this.add = add;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
