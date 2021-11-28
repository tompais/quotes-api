package com.example.quotes.clients.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "clients.chuck-norris-quote")
class ChuckNorrisQuoteProps : BaseQuoteProps()
