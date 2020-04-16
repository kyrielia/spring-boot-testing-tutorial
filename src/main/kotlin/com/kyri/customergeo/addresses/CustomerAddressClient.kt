package com.kyri.customergeo.addresses

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

/**
 * Outbound port for getting customer details. Having this as an interface is essentially the same as a contract
 */
interface CustomerAddressClient {
    @Throws(UnknownCustomerException::class)
    fun getAddressForCustomer(customerId: Int): String
}

class UnknownCustomerException(customerId: Int): ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID $customerId could not be found")