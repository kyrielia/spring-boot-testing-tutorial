package com.kyri.customergeo.geolocation

import java.lang.RuntimeException

interface GeolocationClient {
    @Throws(UnknownAddressException::class)
    fun getGeolocationForAddress(address: String): Geolocation
}

data class Geolocation(
    val lat: Double,
    val lon: Double
)

class UnknownAddressException(address: String): RuntimeException("Address $address could not be found")