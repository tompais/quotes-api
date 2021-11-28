package com.example.quotes.clients.implementations

import com.example.quotes.clients.BaseQuoteClient
import com.example.quotes.clients.properties.BaseQuoteProps
import com.example.quotes.clients.responses.QuoteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class ChuckNorrisQuoteClient(
    @Qualifier("chuckNorrisQuoteWebClient") webClient: WebClient,
    @Qualifier("chuckNorrisQuoteProps") quoteProps: BaseQuoteProps
) : BaseQuoteClient(webClient, quoteProps) {
    override fun getQuotesAsync(count: Int): Flow<QuoteResponse> =
        flow<QuoteResponse> {
            repeat(count) {
                emit(getAsync(quoteProps.getQuotes, mapOf("category" to "dev")))
            }
        }.flowOn(Dispatchers.Default)
}
