package com.test.projects.mimimi.service.vote;

import com.test.projects.mimimi.dto.vote.VotePairDTO;
import com.test.projects.mimimi.dto.vote.VoteSubjectDTO;
import com.test.projects.mimimi.exception.CheaterException;
import com.test.projects.mimimi.exception.ObjectNotFoundException;
import com.test.projects.mimimi.model.UserCategory;
import com.test.projects.mimimi.repository.SubjectCategoryRepository;
import com.test.projects.mimimi.repository.SubjectRepository;
import com.test.projects.mimimi.repository.UserCategoryRepository;
import com.test.projects.mimimi.repository.UserRepository;
import com.test.projects.mimimi.service.subjects.storage.SubjectStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Общий сервис гля голосования, пусть он возьмет на себя управление другими побочными сервисами,
 * например, сервисом кеширования.
 */

@Service
public class VoteServiceImpl implements VoteService {
    private final SubjectStorage subjectStorage;
    private final SubjectRepository subjectRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final SubjectCategoryRepository subjectCategoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public VoteServiceImpl(@Qualifier("subjectStorageFromPostgres") SubjectStorage subjectStorage, SubjectRepository subjectRepository, UserCategoryRepository userCategoryRepository, SubjectCategoryRepository subjectCategoryRepository, UserRepository userRepository) {
        this.subjectStorage = subjectStorage;
        this.subjectRepository = subjectRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.subjectCategoryRepository = subjectCategoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<VotePairDTO> getVoteSubjects(UUID subjectCategoryId) throws ObjectNotFoundException {
        ArrayList<VotePairDTO> votePairs = new ArrayList<>(subjectStorage.getVotePairs(subjectCategoryId));
        Collections.shuffle(votePairs, new Random());
        return votePairs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void vote(List<VotePairDTO> pairs, UUID userId) throws ObjectNotFoundException, RequestRejectedException, CheaterException {
        UUID categoryId = pairs.get(0).getLeftSubject().getSubjectCategoryId();

        userCategoryRepository.getByUser_IdAndSubjectCategory_Id(userId, categoryId)
                .ifPresent(i -> {throw new CheaterException("You can not vote twice");});

        ArrayList<VotePairDTO> votePairs = subjectStorage.getVotePairs(categoryId);

        if (!comparePairs(pairs, votePairs)) {
            throw new RequestRejectedException("Wrong request data");
        }

        Map<UUID, Integer> votedSubjects = new HashMap<>();

        pairs.forEach(item -> {
            VoteSubjectDTO subject;
            if (item.getLeftSubject().getVoted()) {
                subject = item.getLeftSubject();
            } else if (item.getRightSubject().getVoted()) {
                subject = item.getRightSubject();
            } else {
                throw new RequestRejectedException("Wrong request data");
            }

            if (votedSubjects.containsKey(subject.getId())) {
                Integer likes = votedSubjects.get(subject.getId());
                votedSubjects.put(subject.getId(), likes + 1);
            } else {
                votedSubjects.put(subject.getId(), 1);
            }
        });

        votedSubjects.forEach(subjectRepository::setLikesById);
        userCategoryRepository.save(
                new UserCategory(
                        subjectCategoryRepository.findById(categoryId).orElseThrow(() -> new RequestRejectedException("Wrong request data")),
                        userRepository.findById(userId).orElseThrow(() -> new RequestRejectedException("Wrong request data"))
                )
        );
    }

    private Boolean comparePairs(List<VotePairDTO> pairs1, List<VotePairDTO> pairs2) {
        if (pairs1.size() != pairs2.size()) {
            return false;
        }

        Collections.sort(pairs1);
        Collections.sort(pairs2);
        return pairs1.equals(pairs2);
    }
}
