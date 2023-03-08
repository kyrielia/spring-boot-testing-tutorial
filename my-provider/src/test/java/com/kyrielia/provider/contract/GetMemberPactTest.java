package com.kyrielia.provider.contract;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import com.kyrielia.provider.model.Member;
import com.kyrielia.provider.repository.MemberRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("contract")
@ExtendWith(SpringExtension.class)
@Provider("member")
@PactFolder("pacts")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class GetMemberPactTest {

  @MockBean
  private MemberRepository memberRepository;

  @State("member exists")
  void memberExists() {
    when(memberRepository.getReferenceById(any())).thenReturn(Member.builder()
        .firstNames("Kyriacos")
        .surname("Elia")
        .addressLine1("10 Furnival St")
        .city("London")
        .postcode("EC4A 1AB")
        .phoneNumber("01234567890")
        .emailAddress("email@gmail.com")
        .build());
  }

  @TestTemplate
  @ExtendWith(PactVerificationSpringProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }
}
