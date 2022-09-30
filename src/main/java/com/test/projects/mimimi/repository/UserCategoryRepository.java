package com.test.projects.mimimi.repository;

import com.test.projects.mimimi.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserCategoryRepository extends JpaRepository<UserCategory, UUID> {
    List<UserCategory> getByUser_Id(UUID id);
    Optional<UserCategory> getByUser_IdAndSubjectCategory_Id(UUID user_id, UUID subjectCategory_id);
}
