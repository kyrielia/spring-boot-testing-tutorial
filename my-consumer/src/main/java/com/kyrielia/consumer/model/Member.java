package com.kyrielia.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Member {
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String postcode;
}
