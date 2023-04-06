package com.hack.moderationservice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hack.moderationservice.documents.FlaggedMessage;
import com.hack.moderationservice.dtos.FlaggedMessageResponse;
import com.hack.moderationservice.dtos.ModeratePayload;
import com.hack.moderationservice.dtos.ModerateRequest;
import com.hack.moderationservice.dtos.ModerationRes;
import com.hack.moderationservice.dtos.ModerationResponse;
import com.hack.moderationservice.mappers.ModerationMapper;
import com.hack.moderationservice.repositories.FlaggedMessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModerateService {

  private final FlaggedMessageRepository flaggedMessageRepository;
  private final ModerationClient client;
  private final ModerationMapper mapper;
  private final MitigationService mitigationService;

  public ModerationRes moderate(ModerateRequest request) {
    final ModerationResponse result = client.moderateText(new ModeratePayload(request.input()));
    Boolean flagged = result.results().get(0).flagged();
    log.info("Moderation result received and message inappropriate = {}", flagged);
    if (flagged.booleanValue()) {
      final String response = mitigationService.determineMitigation(result.results().get(0), request);
      final FlaggedMessage saved = flaggedMessageRepository.save(mapper.toFlaggedMessage(result, request, response));
      log.info("Saving flagged message {} for user {}", saved.getId(), request.userId());
      return mapper.toModerationResult(saved);
    }

    return ModerationRes.builder().flagged(false).build();
  }

  public Page<FlaggedMessageResponse> getPageOfFlaggedMessages(Pageable pageable) {
    return flaggedMessageRepository.findAll(pageable).map(mapper::toFlaggedResponse);
  }

  public Page<FlaggedMessageResponse> getPageOfFlaggedMessagesForTutor(String tutorId, Pageable pageable) {
    return flaggedMessageRepository.findAllByUserId(tutorId, pageable).map(mapper::toFlaggedResponse);
  }
}
