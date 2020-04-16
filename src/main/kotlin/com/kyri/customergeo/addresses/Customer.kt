package com.kyri.customergeo.addresses

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Customer(
    @Id
    val id: Int,

    val address: String
)