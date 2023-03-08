package com.kyrielia.provider.service;

import com.kyrielia.provider.model.Member;
import com.kyrielia.provider.repository.MemberRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Member getMember(UUID memberId) {
    return memberRepository.getReferenceById(memberId);
  }
}
