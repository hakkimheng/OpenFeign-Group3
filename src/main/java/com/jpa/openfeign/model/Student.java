package com.jpa.openfeign.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String name;
    private String gender;
    private String address;
}
