package com.kyrielia.provider.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kyrielia.provider.model.Member;
import com.kyrielia.provider.repository.MemberRepository;
import java.io.IOException;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@Tag("component")
@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@ExtendWith(SpringExtension.class)
public class GetMemberApiTest {

  private static final UUID MEMBER_ID = UUID.randomUUID();

  @LocalServerPort
  private int port;

  @MockBean
  private MemberRepository memberRepository;

  @Test
  void shouldReturnMemberIfMemberExists() throws IOException {
    Member member = Member.builder()
        .id(MEMBER_ID)
        .firstNames("Kyriacos")
        .surname("Elia")
        .addressLine1("Chancery Exchange")
        .addressLine2("10 Furnival St")
        .city("London")
        .postcode("EC4A 1AB")
        .phoneNumber("01234567890")
        .emailAddress("email@gmail.com")
        .build();

    when(memberRepository.getReferenceById(MEMBER_ID)).thenReturn(member);

    MemberClient memberClient = new MemberClient(port);
    ResponseEntity<String> response = memberClient.getMemberById(MEMBER_ID);

    assertEquals(200, response.getStatusCode().value());

    Member memberFromResponse = new ObjectMapper().readValue(response.getBody(), Member.class);
    assertEquals(member, memberFromResponse);
  }
}
