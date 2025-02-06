package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.thermodynamic.TemperatureDifference
import io.github.frantoso.physicalquantities.utils.toBigDecimal
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class SimpleDifferenceTest {
    class TestDiff(
        value: Number,
    ) : SimpleDifference<TestDiff>(value, "x")

    @Test
    fun `test initialisation`() {
        val difference = TemperatureDifference.fromDegrees(10)

        assertThat(difference.degrees).isEqualTo(10.toBigDecimal())
        assertThat(difference.unitSymbol).isEqualTo("°")
    }

    @Test
    fun `test greater than`() {
        val value1 = TestDiff(41)
        val value2 = TestDiff(42.0)

        assertThat(value1 < value2).isTrue
        assertThat(value1 <= value2).isTrue
        assertThat(value1 > value2).isFalse
        assertThat(value1 >= value2).isFalse
        assertThat(value2 < value1).isFalse
        assertThat(value2 <= value1).isFalse
        assertThat(value2 > value1).isTrue
        assertThat(value2 >= value1).isTrue
    }

    @Test
    fun `test toString`() {
        val value1 = TestDiff(20.3)
        val value2 = TestDiff(-20.6)

        val result1 = value1.toString()
        val result2 = value2.toString()

        assertThat(result1).isEqualTo("20.3 x")
        assertThat(result2).isEqualTo("-20.6 x")
    }
}
