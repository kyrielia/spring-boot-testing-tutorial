package com.kyri.customergeo.contract.geolocation

import com.kyri.customergeo.geolocation.GeolocationClient
import com.kyri.customergeo.geolocation.GoogleApiGeolocationClient
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class GeolocationClientContractTest {
    private val geolocationClient: GeolocationClient = GoogleApiGeolocationClient()

    @Disabled
    @Test
    fun `should get coordinates for an address`() {
        // When I call a real address
        // Then I get the geolocation for that address
    }

    @Disabled
    @Test
    fun `should get coordinates for another address`() {
        // When I call a real address
        // Then I get the geolocation for that address
    }

    @Disabled
    @Test
    fun `should handle attempt to retrieve coordinates for a non-existent address`() {
        // When I call an unknown address
        // Then I get an unknown address exception
    }
}