package io.github.frantoso.physicalquantities.thermodynamic

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._m
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import kotlin.test.Test

class PressureTest {
    @Test
    fun `test initialisation`() {
        val pressure = Pressure.fromPascal(24)

        assertThat(pressure.pascal).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal (Pa)`() {
        val pressure = 24.Pa

        assertThat(pressure.pascal).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal (bar)`() {
        val pressure = 2.3.bar

        assertThat(pressure.pascal).isCloseTo(230_000.0, Offset.offset(0.00000001))
    }

    @Test
    fun `test createFromValue`() {
        val pressure = -(24.Pa) // base class calls createFromValue()

        assertThat(pressure.pascal).isEqualTo(-24.0)
    }

    @Test
    fun `to value with unit (Pa)`() {
        val pressure = 32.Pa

        val result = pressure.Pa

        assertThat(result).isEqualTo(ValueWithUnit(32.0, "", "Pa"))
    }

    @Test
    fun `to value with unit from ScaledQuantity (Pa)`() {
        val pressure = 42.Pa

        val result = pressure._m.Pa

        assertThat(result).isEqualTo(ValueWithUnit(42000.0, "m", "Pa"))
    }

    @Test
    fun `to value with unit (bar)`() {
        val pressure = 3_200_000.Pa

        val result = pressure.bar

        assertThat(result).isEqualTo(ValueWithUnit(32.0, "", "bar"))
    }

    @Test
    fun `to value with unit from ScaledQuantity (bar)`() {
        val pressure = 4_200_000.Pa

        val result = pressure._m.bar

        assertThat(result).isEqualTo(ValueWithUnit(42_000.0, "m", "bar"))
    }
}
