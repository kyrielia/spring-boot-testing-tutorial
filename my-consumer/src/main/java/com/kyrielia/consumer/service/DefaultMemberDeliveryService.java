package com.kyrielia.consumer.service;

import com.kyrielia.consumer.client.MemberClient;
import com.kyrielia.consumer.client.RouteClient;
import com.kyrielia.consumer.model.Address;
import com.kyrielia.consumer.model.Member;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMemberDeliveryService implements MemberDeliveryService {

  public static final Address MY_COMPANY_ADDRESS = Address.builder()
      .addressLine1("Chancery Exchange")
      .addressLine2("10 Furnival Street")
      .city("London")
      .postcode("EC4A 1AB")
      .build();

  // Another team's API
  private final MemberClient memberClient;

 // Third party routing API
  private final RouteClient routeClient;

  @Override
  public List<String> getDeliveryRouteForMember(UUID memberId) {
    Member member = memberClient.getMember(memberId);

    Address memberAddress = Address.builder()
        .addressLine1(member.getAddressLine1())
        .addressLine2(member.getAddressLine2())
        .city(member.getCity())
        .postcode(member.getPostcode())
        .build();

    return routeClient.getRouteInstructions(MY_COMPANY_ADDRESS, memberAddress);
  }
}
