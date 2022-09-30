package com.test.projects.mimimi.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class VoteSubject extends AppEntity {
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String imageUrl;
    @Column(nullable = false)
    private Integer likes = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_category_id", nullable = false)
    private SubjectCategory subjectCategory;
}
