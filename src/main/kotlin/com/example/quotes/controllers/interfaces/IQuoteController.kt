package com.example.quotes.controllers.interfaces

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

interface IQuoteController {
    suspend fun getQuotes(serverRequest: ServerRequest): ServerResponse
}
