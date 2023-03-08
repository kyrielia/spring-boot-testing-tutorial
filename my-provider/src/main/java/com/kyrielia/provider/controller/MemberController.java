package com.kyrielia.provider.controller;

import com.kyrielia.provider.model.Member;
import com.kyrielia.provider.service.MemberService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

  public final MemberService memberService;

  @GetMapping("/member/{memberId}")
  public ResponseEntity<Member> getMember(@PathVariable UUID memberId) {
    Member member = memberService.getMember(memberId);
    return ResponseEntity.ok(member);
  }
}
