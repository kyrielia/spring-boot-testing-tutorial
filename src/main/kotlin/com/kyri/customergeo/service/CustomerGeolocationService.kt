package com.kyri.customergeo.service

import com.kyri.customergeo.addresses.CustomerAddressClient
import com.kyri.customergeo.geolocation.Geolocation
import com.kyri.customergeo.geolocation.GeolocationClient
import org.springframework.stereotype.Service

/**
 * Provides the business logic for retrieving a geolocation for a customer.
 * Not very complex, mind you, but hoping it gives the picture.
 */
interface CustomerGeolocationService {
    fun geolocationForCustomer(customerId: Int): Geolocation
}

@Service
class CustomerGeolocationServiceImpl(
    private val customerAddressClient: CustomerAddressClient,
    private val geolocationClient: GeolocationClient
): CustomerGeolocationService {
    override fun geolocationForCustomer(customerId: Int): Geolocation {
        val address = customerAddressClient.getAddressForCustomer(customerId)
        return geolocationClient.getGeolocationForAddress(address)
    }
}