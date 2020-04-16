package com.kyri.customergeo.addresses

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

interface CustomerAddressClient {
    fun getAddressForCustomer(customerId: String): String
}

class UnknownCustomerException(customerId: String): ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID $customerId could not be found")