package com.example.quotes.config

import com.example.quotes.clients.interfaces.IQuoteClient
import com.example.quotes.enums.QuoteType
import com.example.quotes.enums.QuoteType.CHUCK_NORRIS
import com.example.quotes.enums.QuoteType.INSPIRATIONAL
import com.example.quotes.enums.QuoteType.PROGRAMMING
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuoteClientMapConfig {
    @Bean
    fun quoteClientMap(
        programmingQuoteClient: IQuoteClient,
        inspirationalQuoteClient: IQuoteClient,
        chuckNorrisQuoteClient: IQuoteClient
    ): Map<QuoteType, IQuoteClient> = mapOf(
        PROGRAMMING to programmingQuoteClient,
        INSPIRATIONAL to inspirationalQuoteClient,
        CHUCK_NORRIS to chuckNorrisQuoteClient
    )
}
