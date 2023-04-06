package com.hack.moderationservice.services;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import com.hack.moderationservice.dtos.ModeratePayload;
import com.hack.moderationservice.dtos.ModerationResponse;

public interface ModerationClient {
  @PostExchange("/moderations")
  ModerationResponse moderateText(@RequestBody ModeratePayload payload);
}
