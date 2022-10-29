package com.example.hateoas.web;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.dto.StudentDTO;
import com.example.hateoas.service.StudentService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudents() {
        var studentEntityModels = this.studentService
                .getStudents()
                .stream()
                .map(s -> EntityModel.of(s, getStudentLinks(s)))
                .toList();

        return ResponseEntity.ok(CollectionModel.of(studentEntityModels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable("id") Long id) {
        var studentOpt = this.studentService.getStudentById(id);
        if (studentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var student = studentOpt.get();

        return ResponseEntity.ok(EntityModel.of(student, getStudentLinks(student)));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getStudentOrders(@PathVariable("id") Long id) {
        var orders = this.studentService.getStudentOrders(id)
                .stream()
                .map(o -> EntityModel.of(o, this.getOrderLinks(o)))
                .toList();

        return ResponseEntity.ok(CollectionModel.of(orders));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> updateStudent(@PathVariable("id") Long id, StudentDTO student) {
        throw new NotYetImplementedException();
    }

    private Link[] getStudentLinks(StudentDTO studentDTO) {
        var studentLinks = new ArrayList<Link>();

        var selfRef = linkTo(methodOn(StudentController.class).getStudentById(studentDTO.getId())).withSelfRel();

        studentLinks.add(selfRef);

        if (!studentDTO.isDeleted()) {
            var orderLink = linkTo(methodOn(StudentController.class)
                    .getStudentOrders(studentDTO.getId())).withRel("orders");

            studentLinks.add(orderLink);

            var updateLink = linkTo(methodOn(StudentController.class)
                    .updateStudent(studentDTO.getId(), studentDTO)).withRel("update");

            studentLinks.add(updateLink);
        }

        return studentLinks.toArray(new Link[studentLinks.size()]);
    }

    private Link getOrderLinks(OrderDTO orderDTO) {
        var selfRel = linkTo(methodOn(StudentController.class).getStudentById(orderDTO.getStudentId()))
                .withRel("student");

        return selfRel;
    }
}
