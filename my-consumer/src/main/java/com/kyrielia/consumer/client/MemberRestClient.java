package com.kyrielia.consumer.client;

import com.kyrielia.consumer.model.Member;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MemberRestClient implements MemberClient {

  private final RestTemplate restTemplate;
  private final String baseUrl;

  public MemberRestClient(
      RestTemplate restTemplate,
      @Value("${member.url}") String baseUrl
  ) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  @Override
  public Member getMember(UUID memberId) {
    ResponseEntity<Member> responseEntity = restTemplate.getForEntity(baseUrl + "/member/" + memberId, Member.class);
    return responseEntity.getBody();
  }
}
