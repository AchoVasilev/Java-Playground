package com.example.hateoas.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    public Course() {
        this.orders = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Course setPrice(double price) {
        this.price = price;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Course setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
