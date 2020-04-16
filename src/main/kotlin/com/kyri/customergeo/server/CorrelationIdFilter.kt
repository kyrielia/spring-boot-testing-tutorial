package com.kyri.customergeo.server

import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

const val mdcCorrelationIdKey = "correlationId"
const val correlationIdHeader = "X-Correlation-Id"

/**
 * This is a Spring Boot filter to take a correlation ID from the incoming request, and to set the ID into the
 * application's MDC. This will allow the correlation ID to appear in application logs.
 */
@Component
class CorrelationIdFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        preHandle(httpRequest)
        chain.doFilter(request, response)
        afterCompletion()
    }

    fun preHandle(httpRequest: HttpServletRequest) {
        var correlationId = httpRequest.getHeader(correlationIdHeader)

        if (correlationId == null) {
            // Generate a correlation ID if one has not been provided
            correlationId = UUID.randomUUID().toString()
        }

        MDC.put(mdcCorrelationIdKey, correlationId)
    }

    fun afterCompletion() {
        MDC.remove(mdcCorrelationIdKey)
    }
}