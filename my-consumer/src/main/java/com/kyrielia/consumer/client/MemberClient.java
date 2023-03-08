package com.kyrielia.consumer.client;

import com.kyrielia.consumer.model.Member;
import java.util.UUID;

public interface MemberClient {

  Member getMember(UUID memberId);
}
