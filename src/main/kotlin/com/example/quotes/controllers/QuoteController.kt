package com.example.quotes.controllers

import com.example.quotes.enums.QuoteType
import com.example.quotes.services.interfaces.IQuoteService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.queryParamOrNull

@Component
class QuoteController(
    private val quoteService: IQuoteService
) : BaseController() {
    private fun ServerRequest.getLimit(): Int = getQueryParamAsIntOrDefault("limit", 10)

    private fun ServerRequest.getQuoteTypeOrRandom(): QuoteType =
        queryParamOrNull("quote_type")?.uppercase()?.let(QuoteType::valueOf) ?: QuoteType.values().random()

    suspend fun getQuotes(serverRequest: ServerRequest): ServerResponse =
        buildResponse(quoteService.getQuotesAsync(serverRequest.getQuoteTypeOrRandom(), serverRequest.getLimit()))
}
