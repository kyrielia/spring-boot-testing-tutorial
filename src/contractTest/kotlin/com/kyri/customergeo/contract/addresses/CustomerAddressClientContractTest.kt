package com.kyri.customergeo.contract.addresses

import com.kyri.customergeo.addresses.InMemoryCustomerAddressClient
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


class CustomerAddressClientContractTest {

    private val customerAddressClient = InMemoryCustomerAddressClient()

    @Disabled
    @Test
    fun `retrieve an address for an existing customer`() {
        // Given a customer exists with ID 123 and address 'address'
        // When I call the function with customer 123
        // Then the function should return 'address'
    }

    @Disabled
    @Test
    fun `retrieving an address for a non-existing customer should fail`() {
        // Given a customer 123 does not exist
        // Then the function should throw a 'customer does not exist' exception
    }
}