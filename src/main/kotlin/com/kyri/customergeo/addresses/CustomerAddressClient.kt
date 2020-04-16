package com.kyri.customergeo.addresses

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

interface CustomerAddressClient {
    @Throws(UnknownCustomerException::class)
    fun getAddressForCustomer(customerId: Int): String
}

class UnknownCustomerException(customerId: Int): ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID $customerId could not be found")