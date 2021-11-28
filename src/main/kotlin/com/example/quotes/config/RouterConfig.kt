package com.example.quotes.config

import com.example.quotes.controllers.PingController
import com.example.quotes.controllers.QuoteController
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig {
    @Bean
    @FlowPreview
    fun pingRoutes(pingController: PingController) = coRouter {
        GET("/ping") { _ ->
            pingController.ping()
        }
    }

    @Bean
    @FlowPreview
    fun quoteRoutes(quoteController: QuoteController) = coRouter {
        GET("/quotes", quoteController::getQuotes)
    }
}
