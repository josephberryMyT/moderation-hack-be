package com.hack.moderationservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.hack.moderationservice.documents.FlaggedMessage;

public interface FlaggedMessageRepository extends MongoRepository<FlaggedMessage, String> {
  Page<FlaggedMessage> findAllByUserId(String userId, Pageable pageable);
}
