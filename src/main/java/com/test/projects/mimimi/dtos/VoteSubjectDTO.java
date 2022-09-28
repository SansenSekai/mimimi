package com.test.projects.mimimi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Субъект для голосования
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteSubjectDTO {
    private UUID id;
    private String name;
    private String imageUrl;
    private String subjectCategory;
    private Boolean voted;
}
