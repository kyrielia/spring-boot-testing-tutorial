package com.kyrielia.provider.component;

import java.util.UUID;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MemberClient {

  private final RestTemplate restTemplate = new RestTemplate();

  private final String baseUrl;

  public MemberClient(int port) {
    baseUrl = "http://localhost:" + port;

  }

  public ResponseEntity<String> getMemberById(UUID memberId) {
    return restTemplate.exchange(baseUrl + "/member/" + memberId, HttpMethod.GET, null, String.class);
  }
}
