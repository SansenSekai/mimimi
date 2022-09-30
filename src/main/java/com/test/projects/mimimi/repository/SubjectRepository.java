package com.test.projects.mimimi.repository;

import com.test.projects.mimimi.model.VoteSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<VoteSubject, UUID> {
    ArrayList<VoteSubject> getBySubjectCategory_Id(UUID id);

    @Modifying(clearAutomatically = true)
    @Query("update VoteSubject vs set vs.likes = vs.likes + ?2 where vs.id = ?1")
    void setLikesById(UUID id, Integer likes);
}
