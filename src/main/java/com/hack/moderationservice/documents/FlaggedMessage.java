package com.hack.moderationservice.documents;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hack.moderationservice.dtos.ModerationResult;

import lombok.Data;

@Data
@Document
public class FlaggedMessage {
  @Id
  private String id;
  @CreatedDate
  private Instant createdDate;
  private String userId;
  private String input;
  private String chatId;
  private String model;
  private String response;
  private BigDecimal totalScore;
  private ModerationResult result;
}
