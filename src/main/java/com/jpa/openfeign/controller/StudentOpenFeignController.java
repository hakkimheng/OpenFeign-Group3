package com.jpa.openfeign.controller;

import com.jpa.openfeign.dto.request.StudentRequest;
import com.jpa.openfeign.model.Student;
import com.jpa.openfeign.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student/open-feign")
@Tag(name = "Open Feign")
public class StudentOpenFeignController {

    private final StudentService studentService;


    @GetMapping("/{id}")
    Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    List<Student> getStudents() {
        return studentService.getAllStudent();
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentRequest request) {
        return studentService.createStudent(request);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody StudentRequest request) {
        return studentService.updateStudent(id , request);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Deleted successfully";
    }

}
