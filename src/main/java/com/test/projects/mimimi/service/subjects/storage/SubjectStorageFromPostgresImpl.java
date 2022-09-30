package com.test.projects.mimimi.service.subjects.storage;

import com.test.projects.mimimi.dto.vote.VotePairDTO;
import com.test.projects.mimimi.dto.vote.VoteSubjectDTO;
import com.test.projects.mimimi.exception.ObjectNotFoundException;
import com.test.projects.mimimi.model.SubjectCategory;
import com.test.projects.mimimi.model.VoteSubject;
import com.test.projects.mimimi.repository.SubjectCategoryRepository;
import com.test.projects.mimimi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *  Сервис для кеширования и выдачи списка пар для голосования с использованием базы данных Postgres
 *
 *  @return возвращает именно ArrayList для рандомизирования
 */

@Service("subjectStorageFromPostgres")
@EnableScheduling
public class SubjectStorageFromPostgresImpl implements SubjectStorage {
    private Map<UUID, ArrayList<VotePairDTO>> votePairs = new HashMap<>();

    private final SubjectRepository subjectRepository;
    private final SubjectCategoryRepository categoryRepository;

    @Autowired
    public SubjectStorageFromPostgresImpl(SubjectRepository subjectRepository, SubjectCategoryRepository categoryRepository) {
        this.subjectRepository = subjectRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ArrayList<VotePairDTO> getVotePairs(UUID subjectCategory) throws ObjectNotFoundException {
        ArrayList<VotePairDTO> votePairsDTOs = votePairs.get(subjectCategory);
        if(votePairsDTOs == null) {
            throw new ObjectNotFoundException("Subjects of this category not founded");
        }
        return votePairsDTOs;
    }

    @Scheduled(fixedDelay = 1000 * 60)
    private void updateVotePairs() {
        List<SubjectCategory> categories = categoryRepository.findAll();

        categories.parallelStream().forEach(item -> {
            ArrayList<VoteSubject> subjects = subjectRepository.getBySubjectCategory_Id(item.getId());
            if(subjects.size() < 2) {
                return;
            }

            ArrayList<VotePairDTO> pairs = new ArrayList<>();
            for(int i = 0; i < subjects.size(); i++) {
                for(int j = i + 1; j < subjects.size(); j++) {
                    pairs.add(new VotePairDTO(VoteSubjectDTO.create(subjects.get(i)), VoteSubjectDTO.create(subjects.get(j))));
                }
            }

            votePairs.put(item.getId(), pairs);
        });
    }
}
