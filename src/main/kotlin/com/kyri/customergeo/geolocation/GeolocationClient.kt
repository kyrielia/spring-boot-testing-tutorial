package com.kyri.customergeo.geolocation

interface GeolocationClient {
    fun getGeolocationForAddress(address: String): String
}