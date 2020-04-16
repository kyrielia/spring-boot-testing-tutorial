package com.kyri.customergeo.service

import com.kyri.customergeo.addresses.CustomerDetailsClient
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
    private val customerDetailsClient: CustomerDetailsClient,
    private val geolocationClient: GeolocationClient
): CustomerGeolocationService {
    override fun geolocationForCustomer(customerId: Int): Geolocation {
        val customer = customerDetailsClient.getCustomerDetails(customerId)
        return geolocationClient.getGeolocationForAddress(customer.address)
    }
}