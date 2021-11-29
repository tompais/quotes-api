package com.example.quotes.integration

import com.example.quotes.enums.QuoteType
import com.example.quotes.enums.QuoteType.CHUCK_NORRIS
import com.example.quotes.enums.QuoteType.INSPIRATIONAL
import com.example.quotes.enums.QuoteType.PROGRAMMING
import com.example.quotes.utils.MockUtils.mockQuoteAndAuthorResponse
import com.example.quotes.utils.MockUtils.mockQuoteResponse
import io.restassured.http.Header
import io.restassured.module.webtestclient.RestAssuredWebTestClient.given
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpHeaders.ACCEPT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE

class QuoteIntegrationTest : BaseIntegrationTest() {
    companion object {
        @JvmStatic
        fun getQuotesCases() = listOf<Arguments>(
            Arguments.of(
                "/Quotes",
                PROGRAMMING,
                listOf(mockQuoteAndAuthorResponse())
            ),
            Arguments.of(
                "/quotes",
                INSPIRATIONAL,
                listOf(mockQuoteAndAuthorResponse())
            ),
            Arguments.of(
                "/jokes/random",
                CHUCK_NORRIS,
                mockQuoteResponse()
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getQuotesCases")
    fun getQuotes(quotePath: String, quoteType: QuoteType, response: Any) {
        mockGetRequest(quotePath, response)

        given()
            .header(Header(ACCEPT, APPLICATION_JSON_VALUE))
            .queryParams(
                mapOf(
                    "quote_type" to quoteType
                )
            )
            .`when`()
            .get("/quotes")
            .then()
            .assertThat()
            .status(OK)
    }
}
