package com.kyrielia.consumer.component;

import java.util.UUID;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MyConsumerClient {

  private final RestTemplate restTemplate = new RestTemplate();

  private final String baseUrl;

  public MyConsumerClient(int port) {
    baseUrl = "http://localhost:" + port;
  }

  public ResponseEntity<String> getDeliveryDetails(UUID memberId) {
    return restTemplate.exchange(baseUrl + "/delivery?memberId=" + memberId, HttpMethod.GET, null, String.class);
  }
}
