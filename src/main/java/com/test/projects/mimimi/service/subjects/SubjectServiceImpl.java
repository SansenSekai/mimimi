package com.test.projects.mimimi.service.subjects;

import com.test.projects.mimimi.model.SubjectCategory;
import com.test.projects.mimimi.model.UserCategory;
import com.test.projects.mimimi.model.VoteSubject;
import com.test.projects.mimimi.repository.SubjectCategoryRepository;
import com.test.projects.mimimi.repository.SubjectRepository;
import com.test.projects.mimimi.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectCategoryRepository categoryRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectCategoryRepository categoryRepository, UserCategoryRepository userCategoryRepository, SubjectRepository subjectRepository) {
        this.categoryRepository = categoryRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SubjectCategory> getSubjectCategories() {
        List<SubjectCategory> all = categoryRepository.findAll();
        return all;
    }

    @Override
    public List<SubjectCategory> getAvailableSubjectCategories(UUID id) {
        List<UserCategory> votedCategories = userCategoryRepository.getByUser_Id(id);
        List<UUID> votedCategoriesIds = votedCategories.stream().map(item -> item.getSubjectCategory().getId()).collect(Collectors.toList());
        List<SubjectCategory> availableCategories = votedCategoriesIds.size() == 0 ? categoryRepository.findAll() : categoryRepository.findAllByIdNotIn(votedCategoriesIds);
        return availableCategories;
    }

    @Override
    public List<VoteSubject> getTopSubjects(UUID category, Integer max) {
        Pageable limit = PageRequest.of(0, max);
        List<VoteSubject> voteSubjects = subjectRepository.findAllBySubjectCategory_IdOrderByLikesDesc(category, limit);
        return voteSubjects;
    }
}
