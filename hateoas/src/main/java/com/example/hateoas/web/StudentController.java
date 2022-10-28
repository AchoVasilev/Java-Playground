package com.example.hateoas.web;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.dto.StudentDTO;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable("id") String id) {
        throw new NotYetImplementedException();
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getStudentOrders(@PathVariable("id") String id) {
        throw new NotYetImplementedException();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> updateStudent(@PathVariable("id") String id, StudentDTO student) {
        throw new NotYetImplementedException();
    }
}
