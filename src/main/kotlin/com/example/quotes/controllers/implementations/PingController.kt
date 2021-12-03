package com.example.quotes.controllers.implementations

import com.example.quotes.controllers.BaseController
import com.example.quotes.controllers.interfaces.IPingController
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class PingController : BaseController(), IPingController {
    override suspend fun ping(): ServerResponse = buildResponse("pong", contentType = TEXT_PLAIN)
}
