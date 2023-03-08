package com.kyrielia.consumer.client;

import com.kyrielia.consumer.model.Address;
import java.util.List;

public interface RouteClient {

  List<String> getRouteInstructions(Address address1, Address address2);
}
