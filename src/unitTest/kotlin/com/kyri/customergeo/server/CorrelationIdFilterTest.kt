package com.kyri.customergeo.server

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.internal.stubbing.answers.Returns
import org.slf4j.MDC
import java.util.*
import javax.servlet.http.HttpServletRequest

class CorrelationIdFilterTest {
    private val correlationIdFilter = CorrelationIdFilter()

    @Test
    fun `should set correlation ID in MDC if provided in request`() {
        val expectedCorrelationId = UUID.randomUUID().toString()

        val request = mock(HttpServletRequest::class.java)
        `when`(request.getHeader(correlationIdHeader)).thenAnswer(Returns(expectedCorrelationId))

        correlationIdFilter.preHandle(request)

        val actualCorrelationId = MDC.get(mdcCorrelationIdKey)

        assertEquals(
            expectedCorrelationId,
            actualCorrelationId,
            "Correlation ID mismatch"
        )
    }

    @Test
    fun `should set a correlation ID in MDC if one has not been provided`() {
        val request = mock(HttpServletRequest::class.java)
        `when`(request.getHeader(correlationIdHeader)).thenAnswer(Returns(null))

        correlationIdFilter.preHandle(request)

        val generatedCorrelationId = MDC.get(mdcCorrelationIdKey)
        assertNotNull(generatedCorrelationId, "Should have generated a correlation ID")

        try {
            UUID.fromString(generatedCorrelationId)
        } catch (e: IllegalArgumentException) {
            fail<Any>("Generated correlation ID $generatedCorrelationId should be a valid UUID")
        }
    }

    @Test
    fun `should remove correlation ID from MDC after request`() {
        MDC.put(mdcCorrelationIdKey, UUID.randomUUID().toString())

        correlationIdFilter.afterCompletion()

        assertNull(MDC.get(mdcCorrelationIdKey), "Correlation ID should be null")
    }
}