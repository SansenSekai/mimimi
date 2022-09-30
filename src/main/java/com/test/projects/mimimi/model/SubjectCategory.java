package com.test.projects.mimimi.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject_categories")
public class SubjectCategory extends AppEntity {
    @Column(nullable = false, length = 50, unique = true)
    private String name;
}
