package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._M
import io.github.frantoso.physicalquantities.core._m
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class FrequencyTest {
    @Test
    fun `test initialisation`() {
        val frequency = Frequency.fromHertz(24)

        assertThat(frequency.hertz).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal`() {
        val frequency = 24.Hz

        assertThat(frequency.hertz).isEqualTo(24.0)
    }

    @Test
    fun `test createFromValue`() {
        val frequency = -(24.Hz) // base class calls createFromValue()

        assertThat(frequency.hertz).isEqualTo(-24.0)
    }

    @Test
    fun `to value with unit`() {
        val frequency = 32.Hz

        val result = frequency.Hz

        assertThat(result).isEqualTo(ValueWithUnit(32.0, "", "Hz"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val frequency = 42.Hz

        val result = frequency._m.Hz

        assertThat(result).isEqualTo(ValueWithUnit(42000.0, "m", "Hz"))
    }

    @Test
    fun `from value with unit`() {
        val input = ValueWithUnit(2.3, "M", "Hz")
        val result = Frequency.fromValueWithUnit(input)

        assertThat(result).isEqualTo(2.3._M.Hz)
    }
}
