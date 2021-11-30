package com.example.quotes.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS
import io.netty.handler.logging.LogLevel.INFO
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.ACCEPT
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat.TEXTUAL
import java.time.Duration
import java.util.concurrent.TimeUnit.MILLISECONDS

@Configuration
class WebClientConfig {
    private fun buildHttpClientConfig() = HttpClient.create()
        .option(CONNECT_TIMEOUT_MILLIS, 5000)
        .responseTimeout(Duration.ofMillis(5000))
        .doOnConnected { conn ->
            conn.addHandlerLast(ReadTimeoutHandler(5000, MILLISECONDS))
                .addHandlerLast(WriteTimeoutHandler(5000, MILLISECONDS))
        }.wiretap("reactor.netty.http.client.HttpClient", INFO, TEXTUAL)

    private fun buildExchangeStrategies(objectMapper: ObjectMapper) = ExchangeStrategies
        .builder()
        .codecs { clientDefaultCodecsConfigurer ->
            clientDefaultCodecsConfigurer.defaultCodecs().apply {
                jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper, APPLICATION_JSON))
                jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper, APPLICATION_JSON))
            }
            clientDefaultCodecsConfigurer.customCodecs().apply {
                register(Jackson2JsonDecoder(objectMapper, TEXT_PLAIN))
            }
        }.build()

    private fun buildWebClient(baseUrl: String, objectMapper: ObjectMapper): WebClient = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
        .clientConnector(ReactorClientHttpConnector(buildHttpClientConfig()))
        .exchangeStrategies(buildExchangeStrategies(objectMapper))
        .build()

    @Bean
    fun programmingQuoteWebClient(
        @Value("\${clients.programming-quote.host}") baseUrl: String,
        objectMapper: ObjectMapper
    ): WebClient = buildWebClient(baseUrl, objectMapper)

    @Bean
    fun inspirationalQuoteWebClient(
        @Value("\${clients.inspirational-quote.host}") baseUrl: String,
        objectMapper: ObjectMapper
    ): WebClient = buildWebClient(baseUrl, objectMapper)

    @Bean
    fun chuckNorrisQuoteWebClient(
        @Value("\${clients.chuck-norris-quote.host}") baseUrl: String,
        objectMapper: ObjectMapper
    ): WebClient = buildWebClient(baseUrl, objectMapper)
}
