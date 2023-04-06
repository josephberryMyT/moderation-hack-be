package com.hack.moderationservice.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hack.moderationservice.dtos.FlaggedMessageResponse;
import com.hack.moderationservice.services.ModerateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/flagged")
public class FlaggedMessageController {
  
  private final ModerateService service;

  @GetMapping
  public Page<FlaggedMessageResponse> getFlaggedMessages(Pageable pageable) {
    log.info("Fetching page of flagged messages");
    return service.getPageOfFlaggedMessages(pageable);
  }

  @GetMapping("/tutors/{tutorId}")
  public Page<FlaggedMessageResponse> getFlaggedMessagesForTutor(@PathVariable String tutorId, Pageable pageable) {
    log.info("Fetching page of flagged messages for tutor {}", tutorId);
    return service.getPageOfFlaggedMessagesForTutor(tutorId, pageable);
  }
}
