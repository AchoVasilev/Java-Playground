package com.example.hateoas.service;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.dto.StudentDTO;
import com.example.hateoas.model.entity.Order;
import com.example.hateoas.model.entity.Student;
import com.example.hateoas.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<StudentDTO> getStudentById(Long id) {
        return this.studentRepository
                .findById(id)
                .map(this::map);
    }

    private StudentDTO map(Student entity) {
        var orders = entity.getOrders()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());

        return new StudentDTO()
                .setId(entity.getId())
                .setAge(entity.getAge())
                .setName(entity.getName())
                .setDeleted(entity.isDeleted())
                .setOrders(orders);
    }

    private OrderDTO map(Order entity) {
        return new OrderDTO()
                .setCourseId(entity.getCourse().getId())
                .setStudentId(entity.getStudent().getId());
    }
}
