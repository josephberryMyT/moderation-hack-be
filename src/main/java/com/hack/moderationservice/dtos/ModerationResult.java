package com.hack.moderationservice.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ModerationResult(Categories categories, @JsonAlias("category_scores") CategoryScores scores, Boolean flagged) {
  
}
