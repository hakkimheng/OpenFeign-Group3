package com.jpa.openfeign.controller;

import com.jpa.openfeign.dto.request.StudentRequest;
import com.jpa.openfeign.exception.NotFoundException;
import com.jpa.openfeign.model.Student;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student/rest-template")
@Tag(name = "Rest Template")
public class StudentRestTemplateController {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://67d2fa458bca322cc268b50c.mockapi.io/student";

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        try {
            return restTemplate.getForObject(BASE_URL + "/" + id, Student.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new NotFoundException("Student not found with id: " + id);
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Upstream error", ex);
        }
    }

    @GetMapping
    public List<Student> getStudents() {
        ResponseEntity<List<Student>> resp = restTemplate.exchange(
                BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
        );
        return resp.getBody();
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentRequest student) {
        // If the upstream API expects Student instead of StudentRequest, adjust accordingly.
        return restTemplate.postForObject(BASE_URL, student, Student.class);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody StudentRequest student) {
        HttpEntity<StudentRequest> entity = new HttpEntity<>(student);
        ResponseEntity<Student> resp = restTemplate.exchange(
                BASE_URL + "/" + id,
                HttpMethod.PUT,
                entity,
                Student.class
        );
        return resp.getBody();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}