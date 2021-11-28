package com.example.quotes.clients

import com.example.quotes.clients.interfaces.IQuoteClient
import com.example.quotes.clients.properties.BaseQuoteProps
import org.springframework.web.reactive.function.client.WebClient

abstract class BaseQuoteClient(webClient: WebClient, protected val quoteProps: BaseQuoteProps) :
    BaseRestClient(webClient), IQuoteClient
