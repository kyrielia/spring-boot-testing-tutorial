package com.kyrielia.consumer.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import com.kyrielia.consumer.model.Address;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Tag("external-contract")
@Testcontainers
class RouteRestClientTest {

  @Container
  private static final MockServerContainer mockServerContainer = new MockServerContainer(DockerImageName.parse("mockserver/mockserver"));

  private static final String ADDRESS_LINE_1 = "The Cottage";
  private static final String ADDRESS_LINE_2 = "Willow Way";
  private static final String CITY = "London";
  private static final String POSTCODE = "A9 9AA";

  private final MockServerClient mockServerClient = new MockServerClient(mockServerContainer.getHost(), mockServerContainer.getServerPort());

  @BeforeEach
  void setUp() {
    mockServerClient.reset();
  }

  @Test
  void shouldGetRouteInstructionsForAddress() {
    Address address = Address.builder()
        .addressLine1(ADDRESS_LINE_1)
        .addressLine2(ADDRESS_LINE_2)
        .city(CITY)
        .postcode(POSTCODE)
        .build();

    String instructions = "[\"Instruction 1\", \"Instruction 2\"]";

    mockServerClient
        .when(request().withMethod("post").withPath("/route"))
        .respond(response().withStatusCode(200).withBody(instructions, MediaType.APPLICATION_JSON));

    RouteRestClient routeRestClient = new RouteRestClient(new RestTemplate(), mockServerContainer.getEndpoint());
    List<String> routeInstructions = routeRestClient.getRouteInstructions(address, address);

    assertEquals(2, routeInstructions.size());
    assertTrue(routeInstructions.contains("Instruction 1"));
    assertTrue(routeInstructions.contains("Instruction 2"));
  }
}