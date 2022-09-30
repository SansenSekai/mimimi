package com.test.projects.mimimi.dto.vote;

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
public class VotePairDTO implements Comparable<VotePairDTO> {
    private VoteSubjectDTO leftSubject;
    private VoteSubjectDTO rightSubject;


    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + leftSubject.hashCode();
        result = 31 * result + rightSubject.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        VotePairDTO pair = (VotePairDTO) obj;

        return this.leftSubject.getId().equals(pair.leftSubject.getId()) && this.rightSubject.getId().equals(pair.rightSubject.getId());
    }

    @Override
    public int compareTo(VotePairDTO o) {
        return (leftSubject.getId().toString() + rightSubject.getId().toString()).compareTo(o.getLeftSubject().getId().toString() + o.getRightSubject().getId().toString());
    }
}
