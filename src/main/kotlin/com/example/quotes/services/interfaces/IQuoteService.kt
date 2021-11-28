package com.example.quotes.services.interfaces

import com.example.quotes.clients.responses.QuoteResponse
import com.example.quotes.enums.QuoteType
import kotlinx.coroutines.flow.Flow
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Validated
interface IQuoteService {
    fun getQuotesAsync(
        quoteType: QuoteType,
        @Min(value = 1, message = "The minimum limit is 1") @Max(
            value = 20,
            message = "The maximum limit is 20"
        ) limit: Int = 10
    ): Flow<QuoteResponse>
}
