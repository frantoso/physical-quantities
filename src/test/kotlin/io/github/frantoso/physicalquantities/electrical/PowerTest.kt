package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._m
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class PowerTest {
    @Test
    fun `test initialisation`() {
        val power = Power.fromWatt(24)

        assertThat(power.watt).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal`() {
        val power = 24.W

        assertThat(power.watt).isEqualTo(24.0)
    }

    @Test
    fun `test createFromValue`() {
        val power = -(24.W) // base class calls createFromValue()

        assertThat(power.watt).isEqualTo(-24.0)
    }

    @Test
    fun `to value with unit`() {
        val power = 32.W

        val result = power.W

        assertThat(result).isEqualTo(ValueWithUnit(32.0, "", "W"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val power = 42.W

        val result = power._m.W

        assertThat(result).isEqualTo(ValueWithUnit(42000.0, "m", "W"))
    }

    @Test
    fun `from value with unit`() {
        val input = ValueWithUnit(2.3, "m", "W")
        val result = Power.fromValueWithUnit(input)

        assertThat(result).isEqualTo(2.3._m.W)
    }
}
