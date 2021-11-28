package com.example.quotes.clients.implementations

import com.example.quotes.clients.BaseQuoteClient
import com.example.quotes.clients.properties.BaseQuoteProps
import com.example.quotes.clients.responses.QuoteAndAuthorResponse
import com.example.quotes.clients.responses.QuoteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class InspirationalQuoteClient(
    @Qualifier("inspirationalQuoteWebClient") webClient: WebClient,
    @Qualifier("inspirationalQuoteProps") quoteProps: BaseQuoteProps
) : BaseQuoteClient(webClient, quoteProps) {
    override fun getQuotesAsync(count: Int): Flow<QuoteResponse> = getFlow<QuoteAndAuthorResponse>(quoteProps.getQuotes).take(count)
}
