package com.example.quotes.unit

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.quotes.clients.interfaces.IQuoteClient
import com.example.quotes.enums.QuoteType
import com.example.quotes.services.implementations.QuoteService
import com.example.quotes.utils.MockUtils.mockQuoteResponse
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(MockKExtension::class)
class QuoteServiceTest {
    private val expectedQuotes = flowOf(mockQuoteResponse())

    private val quoteClient = mockk<IQuoteClient>() {
        every { getQuotesAsync(any()) } returns expectedQuotes
    }

    @SpyK
    private var quoteClientMap: Map<QuoteType, IQuoteClient> = QuoteType.values().associateWith { quoteClient }

    @InjectMockKs
    private lateinit var quoteService: QuoteService

    companion object {
        @JvmStatic
        fun getQuotesCases() = QuoteType.values()
    }

    @ParameterizedTest
    @MethodSource("getQuotesCases")
    fun getQuotes(quoteType: QuoteType) {
        // WHEN
        val actualQuotes = quoteService.getQuotesAsync(quoteType)

        // THEN
        assertThat(actualQuotes).isEqualTo(expectedQuotes)
    }
}
