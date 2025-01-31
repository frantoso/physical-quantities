package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._m
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ResistanceTest {
    @Test
    fun `test initialisation`() {
        val resistance = Resistance.fromOhm(24)

        assertThat(resistance.ohm).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal`() {
        val resistance = 24.O

        assertThat(resistance.ohm).isEqualTo(24.0)
    }

    @Test
    fun `test createFromValue`() {
        val resistance = -(24.O) // base class calls createFromValue()

        assertThat(resistance.ohm).isEqualTo(-24.0)
    }

    @Test
    fun `to value with unit`() {
        val resistance = 32.O

        val result = resistance.O

        assertThat(result).isEqualTo(ValueWithUnit(32.0, "", "Ω"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val resistance = 42.O

        val result = resistance._m.O

        assertThat(result).isEqualTo(ValueWithUnit(42000.0, "m", "Ω"))
    }

    @Test
    fun `from value with unit`() {
        val input = ValueWithUnit(2.3, "m", "Ω")
        val result = Resistance.fromValueWithUnit(input)

        assertThat(result).isEqualTo(2.3._m.O)
    }
}
