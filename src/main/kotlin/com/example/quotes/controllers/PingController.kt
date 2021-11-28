package com.example.quotes.controllers

import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class PingController : BaseController() {
    suspend fun ping(): ServerResponse = buildResponse("pong", contentType = TEXT_PLAIN)
}
