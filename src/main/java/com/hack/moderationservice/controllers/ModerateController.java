package com.hack.moderationservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hack.moderationservice.dtos.ModerateRequest;
import com.hack.moderationservice.dtos.ModerationRes;
import com.hack.moderationservice.services.ModerateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RequestMapping("/moderate")
@RestController
@RequiredArgsConstructor
public class ModerateController {

  private final ModerateService service;

  @PostMapping("/text")
  public ModerationRes moderateText(@RequestBody ModerateRequest payload) {
    log.info(String.format("Moderating %s for user %s", payload.input(), payload.userId()));
    return service.moderate(payload);
  }
  
}
