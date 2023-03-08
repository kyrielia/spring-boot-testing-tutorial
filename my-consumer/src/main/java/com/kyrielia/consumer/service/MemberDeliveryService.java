package com.kyrielia.consumer.service;

import java.util.List;
import java.util.UUID;

public interface MemberDeliveryService {
  List<String> getDeliveryRouteForMember(UUID memberId);
}
