package com.test.projects.mimimi.services.vote;

import com.test.projects.mimimi.dtos.VotePairDTO;
import com.test.projects.mimimi.exceptions.ObjectNotFoundException;

import java.util.List;

public interface VoteService {
    List<VotePairDTO> getVoteSubjects(String subjectCategoryId) throws ObjectNotFoundException;
}
