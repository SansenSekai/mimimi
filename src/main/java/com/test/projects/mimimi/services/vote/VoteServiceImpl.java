package com.test.projects.mimimi.services.vote;

import com.test.projects.mimimi.dtos.VotePairDTO;
import com.test.projects.mimimi.exceptions.ObjectNotFoundException;
import com.test.projects.mimimi.services.subjects.SubjectStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Общий сервис гля голосования, пусть он возьмет на себя управление другими побочными сервисами,
 * например, сервисом кеширования.
 */

@Service
public class VoteServiceImpl implements VoteService {
    private final SubjectStorage subjectStorage;

    @Autowired
    public VoteServiceImpl(@Qualifier("subjectStorageFromHardCode") SubjectStorage subjectStorage) {
        this.subjectStorage = subjectStorage;
    }

    @Override
    public List<VotePairDTO> getVoteSubjects(String subjectCategoryId) throws ObjectNotFoundException {
        ArrayList<VotePairDTO> votePairs = new ArrayList<>(subjectStorage.getVotePairs(subjectCategoryId));
        Collections.shuffle(votePairs, new Random());
        return votePairs;
    }
}
