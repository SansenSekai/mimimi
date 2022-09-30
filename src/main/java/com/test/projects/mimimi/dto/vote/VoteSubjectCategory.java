package com.test.projects.mimimi.dto.vote;

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
public class VoteSubjectCategory {
    private UUID id;
    private String name;
}
