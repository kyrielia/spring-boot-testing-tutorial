package com.kyrielia.consumer.component;

import static com.kyrielia.consumer.service.DefaultMemberDeliveryService.MY_COMPANY_ADDRESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyrielia.consumer.client.MemberRestClient;
import com.kyrielia.consumer.client.RouteRestClient;
import com.kyrielia.consumer.model.Address;
import com.kyrielia.consumer.model.Member;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("component")
@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@ExtendWith(SpringExtension.class)
public class DeliveryApiTest {

  private static final UUID MEMBER_ID = UUID.randomUUID();
  private static final String ADDRESS_LINE_1 = "addressLine1";
  private static final String ADDRESS_LINE_2 = "addressLine2";
  private static final String CITY = "city";
  private static final String POSTCODE = "postcode";

  @LocalServerPort
  private int port;

  @MockBean
  private MemberRestClient memberRestClient;

  @MockBean
  private RouteRestClient routeRestClient;

  @Test
  void test() throws JsonProcessingException {
    // Given a member exists
    Member member = Member.builder()
        .addressLine1(ADDRESS_LINE_1)
        .addressLine2(ADDRESS_LINE_2)
        .city(CITY)
        .postcode(POSTCODE)
        .build();

    Address memberAddress = Address.builder()
        .addressLine1(ADDRESS_LINE_1)
        .addressLine2(ADDRESS_LINE_2)
        .city(CITY)
        .postcode(POSTCODE)
        .build();

    when(memberRestClient.getMember(MEMBER_ID)).thenReturn(member);

    // Given instructions to get from company to member's address
    List<String> expectedDeliveryInstructions = List.of("Delivery Instruction 1", "Delivery Instruction 2");
    when(routeRestClient.getRouteInstructions(MY_COMPANY_ADDRESS, memberAddress)).thenReturn(expectedDeliveryInstructions);

    // When I call my application to get the delivery instructions for a customer
    MyConsumerClient consumerClient = new MyConsumerClient(port);
    ResponseEntity<String> response = consumerClient.getDeliveryDetails(MEMBER_ID);

    // Then I should get a 200 OK response
    assertEquals(200, response.getStatusCode().value());

    // Then I should receive the delivery instructions
    List<String> actualDeliveryInstructions = new ObjectMapper().readValue(response.getBody(), new TypeReference<>() {});
    assertEquals(expectedDeliveryInstructions, actualDeliveryInstructions);
  }
}
