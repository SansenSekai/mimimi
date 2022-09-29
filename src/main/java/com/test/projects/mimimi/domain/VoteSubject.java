package com.test.projects.mimimi.domain;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteSubject {
    private UUID id;
    private String name;
    private String imageUrl;
    private UUID subjectCategory;
}
