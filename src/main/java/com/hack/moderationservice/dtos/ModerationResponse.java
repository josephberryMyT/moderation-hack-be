package com.hack.moderationservice.dtos;

import java.util.List;

public record ModerationResponse(String id, String model, List<ModerationResult> results) {
  
}
