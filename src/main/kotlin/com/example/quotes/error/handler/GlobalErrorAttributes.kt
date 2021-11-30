package com.example.quotes.error.handler

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.reactive.function.server.ServerRequest
import javax.validation.ConstraintViolationException
import kotlin.reflect.full.isSubclassOf

@Component
@Order(-2)
class GlobalErrorAttributes : DefaultErrorAttributes() {
    private companion object {
        val BAD_REQUEST_EXCEPTION_CLASSES = setOf(
            ConstraintViolationException::class,
            MethodArgumentNotValidException::class,
            HttpMessageNotReadableException::class,
            JsonMappingException::class,
            MissingKotlinParameterException::class,
            MismatchedInputException::class,
            JsonProcessingException::class
        )
    }

    override fun getErrorAttributes(request: ServerRequest?, options: ErrorAttributeOptions?): MutableMap<String, Any> =
        getError(request).let { error ->
            super.getErrorAttributes(request, options).also { errorAttributes ->
                if (BAD_REQUEST_EXCEPTION_CLASSES.any { error::class.isSubclassOf(it) }) {
                    errorAttributes["status"] = BAD_REQUEST.value()
                    errorAttributes["error"] = BAD_REQUEST.reasonPhrase
                }
            }
        }
}
