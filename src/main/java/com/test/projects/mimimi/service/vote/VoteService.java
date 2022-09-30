package com.test.projects.mimimi.service.vote;

import com.test.projects.mimimi.dto.vote.VotePairDTO;
import com.test.projects.mimimi.exception.CheaterException;
import com.test.projects.mimimi.exception.ObjectNotFoundException;

import java.util.List;
import java.util.UUID;

public interface VoteService {
    List<VotePairDTO> getVoteSubjects(UUID subjectCategoryId) throws ObjectNotFoundException;

    void vote(List<VotePairDTO> pairs, UUID userId) throws ObjectNotFoundException, CheaterException;
}
