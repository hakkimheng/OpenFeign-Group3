package com.jpa.openfeign.controller;


import com.jpa.openfeign.dto.request.StudentRequest;
import com.jpa.openfeign.exception.NotFoundException;
import com.jpa.openfeign.model.Student;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student/web-client")
@Tag(name = "WebClient")
public class StudentWebClientController {

        private final WebClient studentWebClient;

        @GetMapping("/{id}")
        public Mono<Student> getStudent(@PathVariable String id) {
            return studentWebClient.get()
                    .uri("/{id}", id)
                    .retrieve()
                    .onStatus(status -> status.value() == 404,
                            resp -> Mono.error(new NotFoundException("Student not found with id: " + id)))
                    .bodyToMono(Student.class);
        }

        @GetMapping
        public Flux<Student> getStudents() {
            return studentWebClient.get()
                    .retrieve()
                    .bodyToFlux(Student.class);
        }

        @PostMapping
        public Mono<Student> createStudent(@RequestBody StudentRequest req) {
            return studentWebClient.post()
                    .bodyValue(req)
                    .retrieve()
                    .bodyToMono(Student.class);
        }

        @PutMapping("/{id}")
        public Mono<Student> updateStudent(@PathVariable String id, @RequestBody StudentRequest req) {
            return studentWebClient.put()
                    .uri("/{id}", id)
                    .bodyValue(req)
                    .retrieve()
                    .bodyToMono(Student.class);
        }

        @DeleteMapping("/{id}")
        public Mono<Void> deleteStudent(@PathVariable String id) {
            return studentWebClient.delete()
                    .uri("/{id}", id)
                    .retrieve()
                    .toBodilessEntity()
                    .then();
        }
    }
