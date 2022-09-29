package com.test.projects.mimimi.service.vote;

import com.test.projects.mimimi.dto.VotePairDTO;
import com.test.projects.mimimi.exception.ObjectNotFoundException;

import java.util.List;

public interface VoteService {
    List<VotePairDTO> getVoteSubjects(String subjectCategoryId) throws ObjectNotFoundException;
}
