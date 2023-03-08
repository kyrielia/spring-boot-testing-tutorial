package com.kyrielia.consumer.client;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonBody;
import static org.junit.jupiter.api.Assertions.assertEquals;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.kyrielia.consumer.model.Member;
import java.util.UUID;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.client.RestTemplate;

@Tag("contract")
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "member", pactVersion = PactSpecVersion.V3)
class MemberRestClientTest {

  private static final String ADDRESS_LINE_1 = "The Cottage";
  private static final String ADDRESS_LINE_2 = "Willow Way";
  private static final String CITY = "London";
  private static final String POSTCODE = "A9 9AA";

  @Pact(provider = "member", consumer = "delivery")
  public RequestResponsePact memberExists(PactDslWithProvider builder) {
    return builder
        .given("member exists")
        .uponReceiving("Get Member by ID")
          .path("/member/14c9941d-6b21-46d0-966e-eb3f91a69b8e")
          .method("GET")
        .willRespondWith()
          .status(200)
          .body(newJsonBody((o) -> {
            o.stringType("addressLine1", ADDRESS_LINE_1);
            o.stringType("addressLine2", ADDRESS_LINE_2);
            o.stringType("city", CITY);
            o.stringType("postcode", POSTCODE);
          }).build())
        .toPact();
  }

  @Test
  void shouldGetMemberThatExists(MockServer mockServer) {
    MemberRestClient memberRestClient = new MemberRestClient(new RestTemplate(), mockServer.getUrl());

    Member member = memberRestClient.getMember(UUID.fromString("14c9941d-6b21-46d0-966e-eb3f91a69b8e"));

    assertEquals(ADDRESS_LINE_1, member.getAddressLine1());
    assertEquals(ADDRESS_LINE_2, member.getAddressLine2());
    assertEquals(CITY, member.getCity());
    assertEquals(POSTCODE, member.getPostcode());
  }
}