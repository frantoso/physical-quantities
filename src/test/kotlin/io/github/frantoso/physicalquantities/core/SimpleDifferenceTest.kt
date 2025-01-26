package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.thermodynamic.TemperatureDifference
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class SimpleDifferenceTest {
    class TestDiff(
        value: Number,
    ) : SimpleDifference<TestDiff>(value.toDouble())

    @Test
    fun `test initialisation`() {
        val difference = TemperatureDifference.fromDegrees(10)

        assertThat(difference.degrees).isEqualTo(10.0)
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
}
