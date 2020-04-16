package com.kyri.customergeo

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.ResponseEntity

inline fun <reified T: Any> ResponseEntity<String>.isA(result: T.() -> Unit = {}): T {
    return jacksonObjectMapper().readValue(this.body, T::class.java).apply(result)
}