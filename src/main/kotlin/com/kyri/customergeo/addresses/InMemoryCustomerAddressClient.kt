package com.kyri.customergeo.addresses

import org.springframework.stereotype.Component

@Component
class InMemoryCustomerAddressClient : CustomerAddressClient {
    override fun getAddressForCustomer(customerId: String): String {
        TODO("not implemented")
    }
}