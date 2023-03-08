package com.kyrielia.consumer.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.kyrielia.consumer.service.MemberDeliveryService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

  private final MemberDeliveryService memberDeliveryService;

  @GetMapping(value = "/delivery", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<String>> getDeliveryRouteForMember(@RequestParam UUID memberId) {
    List<String> deliveryDetails = memberDeliveryService.getDeliveryRouteForMember(memberId);
    return ResponseEntity.ok(deliveryDetails);
  }
}
