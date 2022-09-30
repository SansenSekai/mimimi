package com.test.projects.mimimi.repository;

import com.test.projects.mimimi.model.SubjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface SubjectCategoryRepository extends JpaRepository<SubjectCategory, UUID> {
    List<SubjectCategory> getAllByIdNotIn(Collection<UUID> id);
}
