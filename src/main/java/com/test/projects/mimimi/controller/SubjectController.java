package com.test.projects.mimimi.controller;

import com.test.projects.mimimi.security.jwt.JwtTokenUtil;
import com.test.projects.mimimi.service.subjects.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = Path.SUBJECT)
public class SubjectController {
    private final SubjectService subjectService;
    private final JwtTokenUtil tokenUtil;

    public SubjectController(SubjectService subjectService, JwtTokenUtil tokenUtil) {
        this.subjectService = subjectService;
        this.tokenUtil = tokenUtil;
    }

    @Autowired


    @RequestMapping(method = RequestMethod.GET, value = "/categories")
    public ResponseEntity<?> getVoteSubjectCategories(){
        try {
            return ResponseEntity.ok(subjectService.getSubjectCategories());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/categories/available")
    public ResponseEntity<?> getAvailableSubjectCategories(@RequestHeader("Authorization") String token){
        try {
            UUID id = tokenUtil.getIdFromToken(token);
            return ResponseEntity.ok(subjectService.getAvailableSubjectCategories(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
