package com.kyrielia.provider.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {

  @Id
  @GeneratedValue
  private UUID id;

  @NotNull
  private String firstNames;

  @NotNull
  private String surname;

  private String phoneNumber;

  @NotNull
  private String addressLine1;

  private String addressLine2;

  @NotNull
  private String city;

  @NotNull
  private String postcode;

  @NotNull
  private String emailAddress;
}
