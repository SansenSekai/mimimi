package com.test.projects.mimimi.controller;

import com.test.projects.mimimi.dto.vote.VotePairDTO;
import com.test.projects.mimimi.exception.ObjectNotFoundException;
import com.test.projects.mimimi.security.jwt.JwtTokenUtil;
import com.test.projects.mimimi.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = Path.VOTE)
public class VoteController {
    private final VoteService voteService;
    private final JwtTokenUtil tokenUtil;

    @Autowired
    public VoteController(VoteService voteService, JwtTokenUtil tokenUtil) {
        this.voteService = voteService;
        this.tokenUtil = tokenUtil;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pairs")
    public ResponseEntity<?> getVotePairs(@RequestParam(required = true) UUID subjectCategory){
        try {
           return ResponseEntity.ok(voteService.getVoteSubjects(subjectCategory));
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/check")
    public ResponseEntity<?> checkVote(@RequestHeader("Authorization") String token, @RequestBody List<VotePairDTO> pairs){
        try {
            UUID userId = tokenUtil.getIdFromToken(token);
            voteService.vote(pairs, userId);
            return ResponseEntity.ok().build();
        } catch (ObjectNotFoundException | RequestRejectedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
