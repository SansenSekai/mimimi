package com.test.projects.mimimi.service.subjects;

import com.test.projects.mimimi.model.SubjectCategory;

import java.util.List;
import java.util.UUID;

public interface SubjectService {
    List<SubjectCategory> getSubjectCategories();
    List<SubjectCategory> getAvailableSubjectCategories(UUID id);
}
