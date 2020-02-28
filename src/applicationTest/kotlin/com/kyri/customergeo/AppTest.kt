package com.kyri.customergeo

import com.kyri.customergeo.addresses.CustomerAddressClient
import com.kyri.customergeo.geolocation.GeolocationClient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.reset
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class AppTest {
    @LocalServerPort
    private var port: Int = 0

    private val baseUrl: String by lazy { "http://localhost:$port" }

    @MockBean
    private lateinit var customerAddressClient: CustomerAddressClient

    @MockBean
    private lateinit var geolocationClient: GeolocationClient

    @AfterEach
    fun reset() {
        reset(customerAddressClient, geolocationClient)
    }

    @Disabled
    @Test
    fun shouldReturnGeolocationForCustomer() {
        // Given customer 123 has address 'address'
        // And address 'address' has geolocation 'geolocation'
        // When I call the API
        // Then I expect a 200 response
        // And the body should have coordinates '0, 0'
    }

//    @Disabled
    @Test
    fun shouldReturn404NotFoundIfCustomerIdDoesNotExist() {
        // Given customer 123 does not exist
        // When I call the API
        // Then I expect a 404 response
    }
}