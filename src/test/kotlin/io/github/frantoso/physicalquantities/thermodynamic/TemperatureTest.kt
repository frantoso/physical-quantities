package io.github.frantoso.physicalquantities.thermodynamic

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._c
import io.github.frantoso.physicalquantities.core._m
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import kotlin.test.Test

@Suppress("NonAsciiCharacters")
class TemperatureTest {
    @Test
    fun `test initialisation`() {
        val temperature = Temperature.fromKelvin(280)

        assertThat(temperature.kelvin).isEqualTo(280.0)
    }

    @Test
    fun `test initialisation from literal (K)`() {
        val temperature = 280.K

        assertThat(temperature.kelvin).isEqualTo(280.0)
    }

    @Test
    fun `test initialisation from literal (°C)`() {
        val temperature = 17.`°C`

        assertThat(temperature.kelvin).isCloseTo(290.15, Offset.offset(0.00000001))
    }

    @Test
    fun `test initialisation from literal (Celsius)`() {
        val temperature = 27.Celsius

        assertThat(temperature.kelvin).isCloseTo(300.15, Offset.offset(0.00000001))
    }

    @Test
    fun `test createFromValue`() {
        val temperature = -(24.K) // base class calls createFromValue()

        assertThat(temperature.kelvin).isEqualTo(-24.0)
    }

    @Test
    fun `to value with unit (K)`() {
        val temperature = 320.K

        val result = temperature.K

        assertThat(result).isEqualTo(ValueWithUnit(320.0, "", "K"))
    }

    @Test
    fun `to value with unit from ScaledQuantity (K)`() {
        val temperature = 30.K

        val result = temperature._m.K

        assertThat(result).isEqualTo(ValueWithUnit(30000.0, "m", "K"))
    }

    @Test
    fun `to value with unit (Celsius)`() {
        val temperature = 320.K

        val result = temperature.Celsius

        assertThat(result.value).isCloseTo(46.85, Offset.offset(0.00000001))
        assertThat(result.symbolPrefix).isEqualTo("")
        assertThat(result.symbolUnit).isEqualTo("°C")
    }

    @Test
    fun `to value with unit from ScaledQuantity (Celsius)`() {
        val temperature = 275.15.K

        val result = temperature._c.Celsius

        assertThat(result).isEqualTo(ValueWithUnit(200.0, "c", "°C"))
    }

    @Test
    fun `to value with unit (°C)`() {
        val temperature = 320.K

        val result = temperature.`°C`

        assertThat(result.value).isCloseTo(46.85, Offset.offset(0.00000001))
        assertThat(result.symbolPrefix).isEqualTo("")
        assertThat(result.symbolUnit).isEqualTo("°C")
    }

    @Test
    fun `to value with unit from ScaledQuantity (°C)`() {
        val temperature = 275.15.K

        val result = temperature._c.`°C`

        assertThat(result).isEqualTo(ValueWithUnit(200.0, "c", "°C"))
    }
}
