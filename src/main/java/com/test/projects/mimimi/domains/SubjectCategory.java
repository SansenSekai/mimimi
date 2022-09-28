package com.test.projects.mimimi.domains;

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
