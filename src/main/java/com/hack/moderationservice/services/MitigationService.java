package com.hack.moderationservice.services;

import org.springframework.stereotype.Service;

import com.hack.moderationservice.dtos.Categories;
import com.hack.moderationservice.dtos.ModerateRequest;
import com.hack.moderationservice.dtos.ModerationResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MitigationService {
  String determineMitigation(ModerationResult moderationResult, ModerateRequest request) {
    flagTutor(request);
    return determineResponse(moderationResult.categories());
  }

  void flagTutor(ModerateRequest request) {
    // TODO trigger ban in matching for example
    log.info("Naughty tutor!!!!!!", request.userId());
  }

  String determineResponse(Categories categories) {
    if (categories.hate().booleanValue()) {
      return "I'm sorry, but hate speech is not tolerated in our company. Please refrain from using language that is intended to degrade, insult, or demean a particular group of people.";
    } else if (categories.hateThreatening().booleanValue()) {
      return "I'm sorry, but any language that is threatening or expresses hatred towards a particular group of people is unacceptable. Please refrain from using such language in the future.";
    } else if (categories.selfHarm().booleanValue()) {
      return "I am concerned about the language you used in your message. If you are experiencing feelings of self-harm or suicide, please reach out to a mental health professional or a helpline for immediate assistance.";
    } else if (categories.sexual().booleanValue()) {
      return "I'm sorry, but any mention of sexual content is inappropriate and violates our company policies. Please refrain from using such language in the future.";
    } else if (categories.sexualMinors().booleanValue()) {
      return "I'm sorry, but any mention of sexual content involving minors is not tolerated in our company. Please refrain from using such language in the future.";
    } else if (categories.violence().booleanValue()) {
      return "I'm sorry, but any language that promotes or glorifies violence is not acceptable in our company. Please refrain from using such language in the future.";
    } else if (categories.violenceGraphic().booleanValue()) {
      return "I'm sorry, but any graphic or violent language is not acceptable in our company. Please refrain from using such language in the future.";
    } else {
      return "Oi";
    }
  }
  
}
