package com.test.projects.mimimi.service.subjects;

import com.test.projects.mimimi.dto.VotePairDTO;
import com.test.projects.mimimi.exception.ObjectNotFoundException;

import java.util.ArrayList;

public interface SubjectStorage {
    ArrayList<VotePairDTO> getVotePairs(String subjectCategories) throws ObjectNotFoundException;
}
