package com.kyri.customergeo.geolocation

import com.github.kittinunf.fuel.Fuel
import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GoogleApiGeolocationClient(
    @Value("\${api.geolocation.google.key}") private val key: String
) : GeolocationClient {
    @Throws(UnknownAddressException::class)
    override fun getGeolocationForAddress(address: String): Geolocation {
        val (_, response, result) = Fuel.get(
            "https://maps.googleapis.com/maps/api/geocode/json",
            listOf(
                "address" to address,
                "key" to key
            )
        )
            .responseString()

        val status: String = JsonPath.read(result.get(), "$.status")

        if (status == "ZERO_RESULTS") {
            throw UnknownAddressException(address)
        }

        val latitude: Double = JsonPath.read(result.get(), "$.results[0].geometry.location.lat")
        val longitude: Double = JsonPath.read(result.get(), "$.results[0].geometry.location.lng")

        return Geolocation(latitude.to3Dp(), longitude.to3Dp())
    }

    private fun Double.to3Dp(): Double {
        return "%.3f".format(this).toDouble()
    }
}