package com.kyri.customergeo.contract.geolocation

import com.kyri.customergeo.geolocation.Geolocation
import com.kyri.customergeo.geolocation.GeolocationClient
import com.kyri.customergeo.geolocation.GoogleApiGeolocationClient
import com.kyri.customergeo.geolocation.UnknownAddressException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class GeolocationClientContractTest {
    private val googleKey: String = System.getenv("GOOGLE_KEY")
    private val geolocationClient: GeolocationClient = GoogleApiGeolocationClient(googleKey)

    @Test
    fun `should get coordinates for an address`() {
        val address = "Buckingham Palace, Westminster, London, SW1A 1AA, UK"

        // When I call a real address
        val geolocation = geolocationClient.getGeolocationForAddress(address)

        // Then I get the geolocation for that address
        assertEquals(Geolocation(51.501, -0.142), geolocation, "Geolocation did not match")
    }

    @Test
    fun `should get coordinates for another address`() {
        val address = "Balmoral Castle, Ballater, AB35 5TB, UK"

        // When I call a real address
        val geolocation = geolocationClient.getGeolocationForAddress(address)

        // Then I get the geolocation for that address
        assertEquals(Geolocation(57.04, -3.229), geolocation, "Geolocation did not match")
    }

    @Test
    fun `should handle attempt to retrieve coordinates for a non-existent address`() {
        val address = "fghsdhlvnushrocngdsrg"

        try {
            // When I call an unknown address
            geolocationClient.getGeolocationForAddress(address)
            fail { "Should have thrown UnknownAddressException" }
        } catch (e: UnknownAddressException) {
            // Then I get an unknown address exception
            assertEquals("Address $address could not be found", e.message, "Exception message did not match")
        }
    }
}