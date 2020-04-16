package com.kyri.customergeo

import com.kyri.customergeo.addresses.CustomerAddressClient
import com.kyri.customergeo.addresses.UnknownCustomerException
import com.kyri.customergeo.geolocation.Geolocation
import com.kyri.customergeo.geolocation.GeolocationClient
import com.kyri.customergeo.geolocation.UnknownAddressException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.fail
import org.mockito.Mockito.`when`
import org.mockito.Mockito.reset
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@ExtendWith(SpringExtension::class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CustomerGeolocationApiTest {
    @LocalServerPort
    private var port: Int = 0

    private val baseUrl: String by lazy { "http://localhost:$port" }

    private val customerId = 123
    private val restTemplate = RestTemplate()

    @MockBean
    private lateinit var customerAddressClient: CustomerAddressClient

    @MockBean
    private lateinit var geolocationClient: GeolocationClient

    @AfterEach
    fun reset() {
        reset(customerAddressClient, geolocationClient)
    }

    @Test
    fun `should return geolocation for customer`() {
        val address = "address"

        // Given customer 123 has address 'address'
        `when`(customerAddressClient.getAddressForCustomer(customerId)).thenReturn(address)

        // And address 'address' has geolocation '0, 0'
        `when`(geolocationClient.getGeolocationForAddress(address)).thenReturn(Geolocation(0.0, 0.0))

        // When I call the API
        val response: ResponseEntity<String> = callGeolocationApi()

        // Then I expect a 200 response
        assertEquals(200, response.statusCodeValue, "Http status code did not match")

        // And the body should have coordinates '0, 0'
        response.isA<Geolocation> {
            assertEquals(0.0, this.lat, "Latitude value did not match")
            assertEquals(0.0, this.lon, "Longitude value did not match")
        }
    }

    @Test
    fun `should return 404 not found if customer id does not exist`() {
        // Given customer 123 does not exist
        `when`(customerAddressClient.getAddressForCustomer(customerId)).thenThrow(UnknownCustomerException(customerId))

        // When I call the API
        try {
            callGeolocationApi()
            fail { "Should thrown exception" }
        } catch (e: HttpStatusCodeException) {
            // Then I expect a 404 response
            assertEquals(404, e.rawStatusCode, "Http status code did not match")
        }
    }

    @Test
    fun `should return 500 if customer address does not exist`() {
        // A customer may have an invalid address in one of two circumstances:
        // 1 - a bug in the address validation process when they register
        // 2 - the customer registered a valid address when they signed up, but now the address no longer exists

        val address = "address"

        // Given customer 123 has address 'address'
        `when`(customerAddressClient.getAddressForCustomer(customerId)).thenReturn(address)

        // And address 'address' does not exist
        `when`(geolocationClient.getGeolocationForAddress(address)).thenThrow(UnknownAddressException(address))

        // When I call the API
        try {
            callGeolocationApi()
            fail { "Should thrown exception" }
        } catch (e: HttpStatusCodeException) {
            // Then I expect a 404 response
            assertEquals(500, e.rawStatusCode, "Http status code did not match")
        }
    }

    private fun callGeolocationApi(): ResponseEntity<String> =
        restTemplate.getForEntity("$baseUrl/customer/$customerId/geolocation", String::class)
}