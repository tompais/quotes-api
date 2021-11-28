package com.example.quotes.error.handler

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
@Order(-2)
class GlobalErrorWebExceptionHandler(
    errorAttributes: ErrorAttributes,
    serverProperties: ServerProperties,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : DefaultErrorWebExceptionHandler(
    errorAttributes,
    WebProperties.Resources(),
    serverProperties.error,
    applicationContext
) {
    init {
        this.setMessageWriters(serverCodecConfigurer.writers)
        this.setMessageReaders(serverCodecConfigurer.readers)
    }

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun logError(request: ServerRequest?, response: ServerResponse?, throwable: Throwable?) =
        logger.error(throwable?.message, throwable)
}
