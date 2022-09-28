package com.test.projects.mimimi.domains;

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
