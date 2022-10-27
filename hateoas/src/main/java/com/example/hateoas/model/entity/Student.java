package com.example.hateoas.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    public Student() {
        this.orders = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    private boolean deleted;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Student setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Student setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
