package com.kyrielia.provider.config;

import com.kyrielia.provider.model.Member;
import com.kyrielia.provider.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * This class is used to populate our DB with items for the purposes of testing. It will wait until the Spring Boot
 * application has finished loading beans into the application context, and then the @PostConstruct block with be
 * triggered.
 */
@Configuration
@RequiredArgsConstructor
public class MembersBootstrapper {

  private final MemberRepository memberRepository;

  @PostConstruct
  public void populateDb() {

    memberRepository.saveAll(List.of(
        Member.builder()
            .id(UUID.fromString("b7539769-8fa7-4243-9064-a6f2f91418a3"))
            .firstNames("Kyriacos")
            .surname("Elia")
            .addressLine1("10 Furnival St")
//            .addressLine2("")
            .city("London")
            .postcode("EC4A 1AB")
            .phoneNumber("01234567890")
            .emailAddress("email@gmail.com")
            .build()
    ));
  }
}
