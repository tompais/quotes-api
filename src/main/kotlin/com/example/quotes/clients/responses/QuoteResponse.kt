package com.example.quotes.clients.responses

import com.fasterxml.jackson.annotation.JsonAlias

open class QuoteResponse(
    @field:JsonAlias("en", "text", "value") val quote: String,
)
