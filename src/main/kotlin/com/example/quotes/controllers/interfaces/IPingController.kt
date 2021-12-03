package com.example.quotes.controllers.interfaces

import org.springframework.web.reactive.function.server.ServerResponse

interface IPingController {
    suspend fun ping(): ServerResponse
}
