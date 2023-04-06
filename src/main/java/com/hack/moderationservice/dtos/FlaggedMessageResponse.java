package com.hack.moderationservice.dtos;

import java.time.Instant;

public record FlaggedMessageResponse(String id, Instant createdDate, String input, String response, String userId) {
}
