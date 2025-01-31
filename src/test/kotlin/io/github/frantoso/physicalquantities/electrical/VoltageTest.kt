package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._m
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class VoltageTest {
    @Test
    fun `test initialisation`() {
        val voltage = Voltage.fromVolt(24)

        assertThat(voltage.volt).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal`() {
        val voltage = 24.V

        assertThat(voltage.volt).isEqualTo(24.0)
    }

    @Test
    fun `test createFromValue`() {
        val voltage = -(24.V) // base class calls createFromValue()

        assertThat(voltage.volt).isEqualTo(-24.0)
    }

    @Test
    fun `to value with unit`() {
        val voltage = 32.V

        val result = voltage.V

        assertThat(result).isEqualTo(ValueWithUnit(32.0, "", "V"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val voltage = 42.V

        val result = voltage._m.V

        assertThat(result).isEqualTo(ValueWithUnit(42000.0, "m", "V"))
    }

    @Test
    fun `from value with unit`() {
        val input = ValueWithUnit(2.3, "m", "V")
        val result = Voltage.fromValueWithUnit(input)

        assertThat(result).isEqualTo(2.3._m.V)
    }
}
