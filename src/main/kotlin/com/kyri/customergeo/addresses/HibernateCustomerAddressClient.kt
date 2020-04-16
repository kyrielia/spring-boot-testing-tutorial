package com.kyri.customergeo.addresses

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

/**
 * Outbound adapter for getting customer data
 */
@Component
class HibernateCustomerDetailsClient(
    private val customerRepository: CustomerRepository
): CustomerDetailsClient {
    @Throws(UnknownCustomerException::class)
    override fun getCustomerDetails(customerId: Int): Customer {
        val maybeCustomer = customerRepository.findById(customerId)
        return if (maybeCustomer.isPresent) maybeCustomer.get() else throw UnknownCustomerException(customerId)
    }
}

interface CustomerRepository : CrudRepository<Customer, Int>