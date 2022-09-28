package com.test.projects.mimimi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Хранит пары для голосования, почему именно такое архитектурное решение я должен был уже объяснить
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotePairDTO {
    private VoteSubjectDTO leftSubject;
    private VoteSubjectDTO rightSubject;
}
