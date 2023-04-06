package com.hack.moderationservice.dtos;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CategoryScores(
    @Field(targetType = FieldType.DECIMAL128) BigDecimal hate,
    @JsonAlias("hate/threatening") @Field(targetType = FieldType.DECIMAL128) BigDecimal hateThreatening,
    @JsonAlias("self-harm") @Field(targetType = FieldType.DECIMAL128) BigDecimal selfHarm,
    @Field(targetType = FieldType.DECIMAL128) BigDecimal sexual,
    @JsonAlias("sexual/minors") @Field(targetType = FieldType.DECIMAL128) BigDecimal sexualMinors,
    @Field(targetType = FieldType.DECIMAL128) BigDecimal violence,
    @JsonAlias("violence/graphic") @Field(targetType = FieldType.DECIMAL128) BigDecimal violenceGraphic
) {

  public BigDecimal getTotalScore() {
    return this.hate.add(this.hateThreatening)
      .add(this.selfHarm).add(this.sexual).add(this.sexualMinors).add(this.violence).add(this.violenceGraphic);
  }
}
