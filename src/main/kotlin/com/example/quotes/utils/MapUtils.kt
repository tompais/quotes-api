package com.example.quotes.utils

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

object MapUtils {
    fun Map<String, Any>.toMultiValueMap(): MultiValueMap<String, String> = LinkedMultiValueMap(
        entries.associate { it.key to listOf(it.value.toString()) }
    )
}
