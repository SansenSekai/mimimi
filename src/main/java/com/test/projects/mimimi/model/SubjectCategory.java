package com.test.projects.mimimi.model;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCategory {
    private UUID id;
    private String name;
}
