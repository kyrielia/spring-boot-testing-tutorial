package com.kyri.customergeo

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.ResponseEntity

/**
 * This function gives a nice inline function to convert a Spring ResponseEntity JSON body into a POJO.
 * This makes it super simple to then verify parameters within that POJO.
 */
inline fun <reified T: Any> ResponseEntity<String>.isA(result: T.() -> Unit = {}): T {
    return jacksonObjectMapper().readValue(this.body, T::class.java).apply(result)
}