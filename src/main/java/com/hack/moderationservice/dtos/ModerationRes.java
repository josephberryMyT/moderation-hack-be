package com.hack.moderationservice.dtos;

import lombok.Builder;

@Builder
public record ModerationRes(String id, Boolean flagged, String response) {
  
}
