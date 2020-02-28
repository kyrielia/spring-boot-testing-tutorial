package com.kyri.customergeo.addresses

interface CustomerAddressClient {
    fun getAddressForCustomer(customerId: String): String
}
