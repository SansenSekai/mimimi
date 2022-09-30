package com.test.projects.mimimi.service.subjects.storage;

import com.test.projects.mimimi.dto.vote.VotePairDTO;
import com.test.projects.mimimi.exception.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.UUID;

public interface SubjectStorage {
    ArrayList<VotePairDTO> getVotePairs(UUID subjectCategory) throws ObjectNotFoundException;
}
