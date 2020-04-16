package com.kyri.customergeo.addresses

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
class HibernateCustomerAddressClient(
    private val customerRepository: CustomerRepository
): CustomerAddressClient {
    @Throws(UnknownCustomerException::class)
    override fun getAddressForCustomer(customerId: Int): String {
        var address = ""

        customerRepository.findById(customerId).ifPresentOrElse(
            { customer -> address = customer.address },
            { throw UnknownCustomerException(customerId) }
        )

        return address
    }
}

interface CustomerRepository : CrudRepository<Customer, Int>