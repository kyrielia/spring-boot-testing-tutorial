package com.kyri.customergeo.service

import com.kyri.customergeo.addresses.CustomerAddressClient
import com.kyri.customergeo.geolocation.Geolocation
import com.kyri.customergeo.geolocation.GeolocationClient
import org.springframework.stereotype.Service

@Service
class CustomerGeolocationService(
    private val customerAddressClient: CustomerAddressClient,
    private val geolocationClient: GeolocationClient
) {
    fun geolocationForCustomer(customerId: Int): Geolocation {
        val address = customerAddressClient.getAddressForCustomer(customerId)
        return geolocationClient.getGeolocationForAddress(address)
    }
}