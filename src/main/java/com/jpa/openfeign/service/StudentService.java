package com.jpa.openfeign.service;


import com.jpa.openfeign.dto.request.StudentRequest;
import com.jpa.openfeign.model.Student;

import java.util.List;

public interface StudentService {
    Student getStudentById(Long id);

    List<Student> getAllStudent();

    Student createStudent(StudentRequest request);

    Student updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);
}
