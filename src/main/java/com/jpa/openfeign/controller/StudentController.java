package com.jpa.openfeign.controller;

import com.jpa.openfeign.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentApiClient client;

    @GetMapping("/{id}")
    Student getStudent(@PathVariable String id) {
        return client.getStudent(id);
    }

    @GetMapping
    List<Student> getStudents() {
        return client.getAllStudent();
    }

}
