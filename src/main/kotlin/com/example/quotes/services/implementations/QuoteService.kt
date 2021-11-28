package com.example.quotes.services.implementations

import com.example.quotes.clients.interfaces.IQuoteClient
import com.example.quotes.clients.responses.QuoteResponse
import com.example.quotes.enums.QuoteType
import com.example.quotes.services.interfaces.IQuoteService
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class QuoteService(private val quoteClientMap: Map<QuoteType, IQuoteClient>) :
    IQuoteService {
    override fun getQuotesAsync(quoteType: QuoteType, limit: Int): Flow<QuoteResponse> =
        quoteClientMap[quoteType]!!.getQuotesAsync(limit)
}
