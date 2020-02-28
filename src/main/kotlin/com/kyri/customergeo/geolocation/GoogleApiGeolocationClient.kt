package com.kyri.customergeo.geolocation

import org.springframework.stereotype.Component

@Component
class GoogleApiGeolocationClient : GeolocationClient {
//    https://maps.googleapis.com/maps/api/geocode/json?address=address&key=key

    override fun getGeolocationForAddress(address: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}