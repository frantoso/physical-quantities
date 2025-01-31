package io.github.frantoso.physicalquantities.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import java.text.NumberFormat
import java.util.Locale
import kotlin.test.Test

class UtilsTest {
    @AfterEach
    fun cleanup() {
        resetDefaultNumberFormatter()
    }

    @Test
    fun `get default number formatter`() {
        val defaultFormatter = defaultNumberFormatter

        assertThat(defaultFormatter.minimumFractionDigits).isEqualTo(1)
        assertThat(defaultFormatter.maximumFractionDigits).isEqualTo(6)
    }

    @Test
    fun `set custom default number formatter`() {
        val formatter = NumberFormat.getNumberInstance(Locale.US)
        formatter.maximumFractionDigits = 3
        formatter.minimumFractionDigits = 2
        setDefaultNumberFormatter(formatter)

        val defaultFormatter = defaultNumberFormatter

        assertThat(defaultFormatter.minimumFractionDigits).isEqualTo(2)
        assertThat(defaultFormatter.maximumFractionDigits).isEqualTo(3)
    }
}
