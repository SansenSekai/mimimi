package com.test.projects.mimimi.service.subjects;

import com.test.projects.mimimi.model.SubjectCategory;
import com.test.projects.mimimi.model.UserCategory;
import com.test.projects.mimimi.repository.SubjectCategoryRepository;
import com.test.projects.mimimi.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectCategoryRepository categoryRepository;
    private final UserCategoryRepository userCategoryRepository;

    public SubjectServiceImpl(SubjectCategoryRepository categoryRepository, UserCategoryRepository userCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.userCategoryRepository = userCategoryRepository;
    }

    @Autowired


    @Override
    public List<SubjectCategory> getSubjectCategories() {
        List<SubjectCategory> all = categoryRepository.findAll();
        return all;
    }

    @Override
    public List<SubjectCategory> getAvailableSubjectCategories(UUID id) {
        List<UserCategory> votedCategories = userCategoryRepository.getByUser_Id(id);
        List<UUID> votedCategoriesIds = votedCategories.stream().map(item -> item.getSubjectCategory().getId()).collect(Collectors.toList());
        List<SubjectCategory> availableCategories = categoryRepository.getAllByIdNotIn(votedCategoriesIds);
        return availableCategories;
    }
}
