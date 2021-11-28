package com.example.quotes.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class QuoteType {
    PROGRAMMING,
    INSPIRATIONAL,
    CHUCK_NORRIS;

    @JsonValue
    override fun toString(): String = name.lowercase()
}
