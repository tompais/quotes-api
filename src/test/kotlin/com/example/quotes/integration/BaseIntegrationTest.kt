package com.example.quotes.integration

import com.example.quotes.utils.BaseMockServerTest
import io.restassured.module.webtestclient.RestAssuredWebTestClient
import org.junit.jupiter.api.BeforeAll
import org.mockserver.client.MockServerClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext

abstract class BaseIntegrationTest : BaseMockServerTest() {
    @Autowired
    protected lateinit var applicationContext: ReactiveWebApplicationContext

    @BeforeAll
    override fun beforeAll(mockServerClient: MockServerClient) {
        RestAssuredWebTestClient.applicationContextSetup(applicationContext)
        super.beforeAll(mockServerClient)
    }
}
