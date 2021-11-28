package com.example.quotes.clients.interfaces

import com.example.quotes.clients.responses.QuoteResponse
import kotlinx.coroutines.flow.Flow

interface IQuoteClient {
    fun getQuotesAsync(count: Int = 10): Flow<QuoteResponse>
}
