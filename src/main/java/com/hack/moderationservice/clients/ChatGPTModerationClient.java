package com.hack.moderationservice.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.hack.moderationservice.services.ModerationClient;

@Configuration
public class ChatGPTModerationClient {
  @Bean
  HttpServiceProxyFactory httpFactory(WebClient client) {
    return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
  }

  @Bean
  ModerationClient client(HttpServiceProxyFactory factory) {
    return factory.createClient(ModerationClient.class);
  }
}
