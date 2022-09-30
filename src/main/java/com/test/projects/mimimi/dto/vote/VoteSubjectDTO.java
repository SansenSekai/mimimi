package com.test.projects.mimimi.dto.vote;

import com.test.projects.mimimi.model.VoteSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Субъект для голосования
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteSubjectDTO {
    private UUID id;
    private String name;
    private String imageUrl;
    private UUID subjectCategoryId;
    private Boolean voted;

    public static VoteSubjectDTO create(VoteSubject voteSubject) {
        VoteSubjectDTO dto = new VoteSubjectDTO();

        dto.setId(voteSubject.getId());
        dto.setName(voteSubject.getName());
        dto.setImageUrl(voteSubject.getImageUrl());
        dto.setSubjectCategoryId(voteSubject.getSubjectCategory().getId());
        dto.setVoted(false);
        return dto;
    }
}
