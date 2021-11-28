package com.example.quotes.clients.implementations

import com.example.quotes.clients.BaseQuoteClient
import com.example.quotes.clients.properties.BaseQuoteProps
import com.example.quotes.clients.responses.QuoteAndAuthorResponse
import com.example.quotes.clients.responses.QuoteResponse
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class ProgrammingQuoteClient(
    @Qualifier("programmingQuoteWebClient") webClient: WebClient,
    @Qualifier("programmingQuoteProps") quoteProps: BaseQuoteProps
) : BaseQuoteClient(webClient, quoteProps) {
    override fun getQuotesAsync(count: Int): Flow<QuoteResponse> =
        getFlow<QuoteAndAuthorResponse>(quoteProps.getQuotes, mapOf("count" to count))
}
