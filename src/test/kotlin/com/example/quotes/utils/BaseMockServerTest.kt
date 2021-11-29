package com.example.quotes.utils

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.mockserver.client.MockServerClient
import org.mockserver.junit.jupiter.MockServerExtension
import org.mockserver.junit.jupiter.MockServerSettings
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse
import org.mockserver.model.HttpResponse.response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration
@TestInstance(PER_CLASS)
@MockServerSettings(ports = [8081])
@ExtendWith(MockKExtension::class, MockServerExtension::class)
abstract class BaseMockServerTest {
    @Autowired
    protected lateinit var objectMapper: ObjectMapper
    protected lateinit var mockServerClient: MockServerClient

    @AfterEach
    fun tearDown() {
        mockServerClient.reset()
    }

    @BeforeAll
    protected fun beforeAll(mockServerClient: MockServerClient) {
        this.mockServerClient = mockServerClient
    }

    @AfterAll
    fun afterAll() {
        mockServerClient.close()
    }

    private fun objectToJson(`object`: Any): String = objectMapper.writeValueAsString(`object`)

    private fun getRequestDefinition(
        httpMethod: HttpMethod, path: String
    ): HttpRequest = request()
        .withMethod(httpMethod.name)
        .withPath(path)

    private fun getResponseDefinition(responseBody: Any, httpStatus: HttpStatus = OK): HttpResponse =
        response()
            .withBody(objectToJson(responseBody))
            .withHeader(Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
            .withStatusCode(httpStatus.value())

    private fun mockServerRequest(
        httpMethod: HttpMethod,
        path: String,
        responseBody: Any
    ) {
        mockServerClient.`when`(getRequestDefinition(httpMethod, path))
            .respond(getResponseDefinition(responseBody))
    }

    protected fun mockGetRequest(
        path: String,
        responseBody: Any
    ) = mockServerRequest(GET, path, responseBody)
}
