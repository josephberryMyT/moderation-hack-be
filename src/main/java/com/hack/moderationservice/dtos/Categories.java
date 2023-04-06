package com.hack.moderationservice.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Categories(
    Boolean hate,
    @JsonAlias("hate/threatening") Boolean hateThreatening,
    @JsonAlias("self-harm") Boolean selfHarm,
    Boolean sexual,
    @JsonAlias("sexual/minors")  Boolean sexualMinors,
    Boolean violence,
    @JsonAlias("violence/graphic") Boolean violenceGraphic
) {
  
}
