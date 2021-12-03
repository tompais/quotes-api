package com.example.quotes.config

import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.DeserializationFeature.READ_ENUMS_USING_TO_STRING
import com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import java.util.TimeZone

@Configuration
class ObjectMapperConfig {
    @Bean
    @Primary
    fun snakeCaseMapper(): ObjectMapper = jacksonMapperBuilder()
        .addModule(JavaTimeModule())
        .enable(WRITE_ENUMS_USING_TO_STRING)
        .enable(READ_ENUMS_USING_TO_STRING)
        .enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
        .disable(WRITE_DATES_AS_TIMESTAMPS)
        .disable(FAIL_ON_UNKNOWN_PROPERTIES)
        .defaultTimeZone(TimeZone.getTimeZone("UTC"))
        .propertyNamingStrategy(SNAKE_CASE)
        .build()

    @Bean
    fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter =
        MappingJackson2HttpMessageConverter(snakeCaseMapper())
}
