package com.example.quotes.clients

import com.example.quotes.utils.RestClientUtils.queryParams
import kotlinx.coroutines.flow.Flow
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.bodyToFlow

abstract class BaseRestClient(
    protected val webClient: WebClient
) {
    protected inline fun <reified T> getFlow(path: String, queryParams: Map<String, Any> = emptyMap()): Flow<T> =
        webClient.get()
            .uri { uriBuilder -> uriBuilder.path(path).queryParams(queryParams).build() }
            .retrieve()
            .bodyToFlow()

    protected suspend inline fun <reified T> getAsync(path: String, queryParams: Map<String, Any> = emptyMap()): T =
        webClient.get()
            .uri { uriBuilder -> uriBuilder.path(path).queryParams(queryParams).build() }
            .retrieve()
            .awaitBody()
}
