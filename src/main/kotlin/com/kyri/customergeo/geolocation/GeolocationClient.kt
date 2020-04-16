package com.kyri.customergeo.geolocation

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

interface GeolocationClient {
    @Throws(UnknownAddressException::class)
    fun getGeolocationForAddress(address: String): Geolocation
}

class UnknownAddressException(address: String): ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Address $address could not be found")