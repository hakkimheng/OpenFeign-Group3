package com.jpa.openfeign.client;

import com.jpa.openfeign.dto.request.StudentRequest;
import com.jpa.openfeign.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "studentAPI",
        url = "https://67d2fa458bca322cc268b50c.mockapi.io/student"
)
public interface StudentApiClient {

    @GetMapping
    List<Student> getAllStudent();

    @GetMapping("/{id}")
    Student getStudentById(@PathVariable Long id);

    @PostMapping
    Student createStudent(StudentRequest request);

    @PutMapping("/{id}")
    Student updateStudent(@PathVariable Long id, StudentRequest request);

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id);
}
