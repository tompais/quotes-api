package com.example.quotes.controllers.implementations

import com.example.quotes.controllers.BaseController
import com.example.quotes.controllers.interfaces.IQuoteController
import com.example.quotes.enums.QuoteType
import com.example.quotes.services.interfaces.IQuoteService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class QuoteController(
    private val quoteService: IQuoteService
) : BaseController(), IQuoteController {
    private fun ServerRequest.getLimit(): Int = getQueryParamAsIntOrDefault("limit", 10)

    private fun ServerRequest.getQuoteTypeOrRandom(): QuoteType =
        getEnumValueOrRandom("quote_type")

    override suspend fun getQuotes(serverRequest: ServerRequest): ServerResponse =
        buildResponse(quoteService.getQuotesAsync(serverRequest.getQuoteTypeOrRandom(), serverRequest.getLimit()))
}
