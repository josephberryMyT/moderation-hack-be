package com.hack.moderationservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hack.moderationservice.documents.FlaggedMessage;
import com.hack.moderationservice.dtos.FlaggedMessageResponse;
import com.hack.moderationservice.dtos.ModerateRequest;
import com.hack.moderationservice.dtos.ModerationRes;
import com.hack.moderationservice.dtos.ModerationResponse;

@Mapper(componentModel = "spring")
public interface ModerationMapper {

  @Mapping(target = "chatId", source = "moderationResponse.id")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "input", source = "request.input")
  @Mapping(target = "userId", source = "request.userId")
  @Mapping(target = "model", source = "moderationResponse.model")
  @Mapping(target = "result", expression = "java(moderationResponse.results().get(0))")
  @Mapping(target = "response", source = "response")
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "totalScore", expression = "java(moderationResponse.results().get(0).scores().getTotalScore())")
  public FlaggedMessage toFlaggedMessage(ModerationResponse moderationResponse, ModerateRequest request, String response);

  @Mapping(target = "flagged", expression = "java(flaggedMessage.getResult().flagged())")
  @Mapping(target = "id", source = "flaggedMessage.id")
  @Mapping(target = "response", source = "flaggedMessage.response")
  public ModerationRes toModerationResult(FlaggedMessage flaggedMessage);

  public FlaggedMessageResponse toFlaggedResponse(FlaggedMessage flaggedMessage);
}
