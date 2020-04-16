package com.kyri.customergeo.contract.addresses

import com.kyri.customergeo.addresses.Customer
import com.kyri.customergeo.addresses.CustomerRepository
import com.kyri.customergeo.addresses.HibernateCustomerAddressClient
import com.kyri.customergeo.addresses.UnknownCustomerException
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CustomerAddressClientContractTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var hibernateCustomerAddressClient: HibernateCustomerAddressClient

    @AfterEach
    fun cleanUp() {
        customerRepository.deleteAll()
    }

    @Test
    fun `retrieve an address for an existing customer`() {
        val address = "address"
        val customerId = 123

        // Given a customer exists with ID 123 and address 'address'
        val customer = Customer(customerId, address)
        customerRepository.save(customer).id

        // When I call the function with customer 123
        val actualAddress = hibernateCustomerAddressClient.getAddressForCustomer(customerId)

        // Then the function should return 'address'
        assertEquals(address, actualAddress, "Addresses did not match")
    }

    @Test
    fun `retrieving an address for a non-existing customer should fail`() {
        val customerId = 123

        // Given a customer 123 does not exist
        // Then the function should throw a 'customer does not exist' exception
        try {
            hibernateCustomerAddressClient.getAddressForCustomer(customerId)
            fail { "Should have thrown exception" }
        } catch (e: UnknownCustomerException) {
            assertEquals("Customer with ID $customerId could not be found", e.reason, "Message did not match")
        }
    }
}