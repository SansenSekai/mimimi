package com.test.projects.mimimi.services.subjects;

import com.test.projects.mimimi.dtos.VotePairDTO;
import com.test.projects.mimimi.exceptions.ObjectNotFoundException;

import java.util.ArrayList;

public interface SubjectStorage {
    ArrayList<VotePairDTO> getVotePairs(String subjectCategories) throws ObjectNotFoundException;
}
