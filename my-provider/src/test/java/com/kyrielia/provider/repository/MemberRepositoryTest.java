package com.kyrielia.provider.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.kyrielia.provider.model.Member;
import java.util.UUID;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Tag("integration")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@Testcontainers
class MemberRepositoryTest {

  @Container
  private static final PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:alpine")
      .withDatabaseName("postgres")
      .withUsername("postgres")
      .withPassword("postgres");

  @DynamicPropertySource
  private static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
  }

  @Autowired
  private MemberRepository memberRepository;

  @Test
  void memberRepositoryIsSetUpCorrectly() {
    Member memberToSave = Member.builder()
        .firstNames("Kyriacos")
        .surname("Elia")
        .addressLine1("Chancery Exchange")
        .addressLine2("10 Furnival St")
        .city("London")
        .postcode("EC4A 1AB")
        .phoneNumber("01234567890")
        .emailAddress("email@gmail.com")
        .build();

    UUID savedId = memberRepository.save(memberToSave).getId();
    assertNotNull(savedId, "Expected an ID to be generated after saving member");

    Member memberFromDb = memberRepository.getReferenceById(savedId);
    memberToSave.setId(savedId);

    assertEquals(memberToSave, memberFromDb, "Member did not match");
  }
}