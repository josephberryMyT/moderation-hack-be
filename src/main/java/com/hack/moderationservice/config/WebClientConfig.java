package com.hack.moderationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  WebClient webClient(@Value("${chatGpt.url}") String baseUrl, @Value("${chatGpt.token}") String chatGptToken) {
    return WebClient.builder().baseUrl(baseUrl)
      .filter((request, next) -> next.exchange(withBearerAuth(request, chatGptToken)))
      .build();
  }

  private static ClientRequest withBearerAuth(ClientRequest request, String token) {
    return ClientRequest.from(request)
      .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
      .build();
  }

}
