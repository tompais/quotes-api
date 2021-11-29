package com.example.quotes.utils

import com.example.quotes.clients.responses.QuoteAndAuthorResponse
import com.example.quotes.clients.responses.QuoteResponse
import com.example.quotes.utils.TestConstant.AUTHOR
import com.example.quotes.utils.TestConstant.QUOTE

object MockUtils {
    fun mockQuoteResponse(quote: String = QUOTE): QuoteResponse = QuoteResponse(quote)
    fun mockQuoteAndAuthorResponse(quote: String = QUOTE, author: String = AUTHOR): QuoteAndAuthorResponse =
        QuoteAndAuthorResponse(quote, author)
}
