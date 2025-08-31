package com.jpa.openfeign.controller;

import com.jpa.openfeign.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "studentAPI",
        url = "https://67d2fa458bca322cc268b50c.mockapi.io/student"
)
public interface StudentApiClient {

    @GetMapping
    List<Student> getAllStudent();

    @GetMapping("/{id}")
    Student getStudent(@PathVariable String id);
}
