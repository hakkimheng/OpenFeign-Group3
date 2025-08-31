package com.jpa.openfeign.service;

import com.jpa.openfeign.client.StudentApiClient;
import com.jpa.openfeign.dto.request.StudentRequest;
import com.jpa.openfeign.exception.NotFoundException;
import com.jpa.openfeign.model.Student;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl  implements StudentService {

    private final StudentApiClient client;
    @Override
    public Student getStudentById(Long id) {
         try {
            return client.getStudentById(id);
         }catch (feign.FeignException.NotFound e){
             throw new NotFoundException("student not found");
         }
    }

    @Override
    public List<Student> getAllStudent() {
        return client.getAllStudent();
    }

    @Override
    public Student createStudent(StudentRequest request) {
        return client.createStudent(request);
    }

    @Override
    public Student updateStudent(Long id, StudentRequest request) {
        getStudentById(id);
        return client.updateStudent(id, request);
    }

    @Override
    public void deleteStudent(Long id) {
        getStudentById(id);
        client.deleteStudent(id);
    }
}
