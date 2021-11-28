package com.example.quotes.utils

import com.example.quotes.utils.MapUtils.toMultiValueMap
import org.springframework.web.util.UriBuilder

object RestClientUtils {
    fun UriBuilder.queryParams(queryParams: Map<String, Any>): UriBuilder =
        queryParams(queryParams.toMultiValueMap())
}
