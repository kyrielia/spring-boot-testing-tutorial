package com.kyrielia.consumer.client;

import com.kyrielia.consumer.model.Address;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RouteRestClient implements RouteClient {

  private final RestTemplate restTemplate;
  private final String baseUrl;

  public RouteRestClient(
      RestTemplate restTemplate,
      @Value("${route.url}") String baseUrl
  ) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  @Override
  public List<String> getRouteInstructions(Address fromAddress, Address toAddress) {
    HttpEntity<?> requestBody = new HttpEntity<>(new RouteRequest(fromAddress, toAddress));
    ResponseEntity<List<String>> responseEntity = restTemplate.exchange(baseUrl + "/route", HttpMethod.POST, requestBody, new ParameterizedTypeReference<>(){});
    return responseEntity.getBody();
  }

  @Data
  private static class RouteRequest {
    private final Address from;
    private final Address to;
  }
}
