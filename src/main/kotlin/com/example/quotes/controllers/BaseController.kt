package com.example.quotes.controllers

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.queryParamOrNull
import org.springframework.web.server.ResponseStatusException

abstract class BaseController {
    protected suspend fun buildResponse(
        body: Any,
        httpStatus: HttpStatus = OK,
        contentType: MediaType = APPLICATION_JSON
    ): ServerResponse =
        ServerResponse.status(httpStatus).contentType(contentType).bodyValueAndAwait(body)

    protected suspend inline fun <reified T : Any> buildResponse(
        flow: Flow<T>,
        httpStatus: HttpStatus = OK,
        contentType: MediaType = APPLICATION_JSON
    ): ServerResponse =
        ServerResponse.status(httpStatus).contentType(contentType).bodyAndAwait(flow)

    protected fun ServerRequest.getQueryParamAsIntOrDefault(key: String, defaultValue: Int): Int = try {
        queryParamOrNull(key)?.toInt() ?: defaultValue
    } catch (nfe: NumberFormatException) {
        throw ResponseStatusException(BAD_REQUEST, "Couldn't parse query param [$key] to a number", nfe)
    }

    protected inline fun <reified T : Enum<T>> ServerRequest.getEnumValueOrNull(key: String): T? =
        queryParamOrNull(key)?.uppercase()?.let<String, T>(::enumValueOf)

    protected inline fun <reified T : Enum<T>> ServerRequest.getEnumValueOrDefault(key: String, defaultValue: T): T =
        getEnumValueOrNull<T>(key) ?: defaultValue
}
