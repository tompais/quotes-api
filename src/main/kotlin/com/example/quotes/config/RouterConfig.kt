package com.example.quotes.config

import com.example.quotes.controllers.interfaces.IPingController
import com.example.quotes.controllers.interfaces.IQuoteController
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig {
    @Bean
    @FlowPreview
    fun pingRoutes(pingController: IPingController) = coRouter {
        GET("/ping") {
            pingController.ping()
        }
    }

    @Bean
    @FlowPreview
    fun quoteRoutes(quoteController: IQuoteController) = coRouter {
        GET("/quotes", quoteController::getQuotes)
    }
}
