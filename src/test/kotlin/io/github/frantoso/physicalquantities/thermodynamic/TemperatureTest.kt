package io.github.frantoso.physicalquantities.thermodynamic

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._c
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.testUtils.STANDARD_OFFSET
import io.github.frantoso.physicalquantities.utils.toBigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

@Suppress("NonAsciiCharacters")
class TemperatureTest {
    @Test
    fun `test initialisation`() {
        val temperature = Temperature.fromKelvin(280)

        assertThat(temperature.kelvin).isEqualTo(280.toBigDecimal())
    }

    @Test
    fun `test initialisation from literal (K)`() {
        val temperature = 280.K

        assertThat(temperature.kelvin).isEqualTo(280.toBigDecimal())
    }

    @Test
    fun `test initialisation from literal (°C)`() {
        val temperature = 17.`°C`

        assertThat(temperature.kelvin).isCloseTo(290.15.toBigDecimal(), STANDARD_OFFSET)
    }

    @Test
    fun `test initialisation from literal (Celsius)`() {
        val temperature = 27.Celsius

        assertThat(temperature.kelvin).isCloseTo(300.15.toBigDecimal(), STANDARD_OFFSET)
    }

    @Test
    fun `test createFromValue`() {
        val temperature = -(24.K) // base class calls createFromValue()

        assertThat(temperature.kelvin).isEqualTo((-24).toBigDecimal())
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

        assertThat(result.value).isCloseTo(46.85.toBigDecimal(), STANDARD_OFFSET)
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

        assertThat(result.value).isCloseTo(46.85.toBigDecimal(), STANDARD_OFFSET)
        assertThat(result.symbolPrefix).isEqualTo("")
        assertThat(result.symbolUnit).isEqualTo("°C")
    }

    @Test
    fun `to value with unit from ScaledQuantity (°C)`() {
        val temperature = 275.15.K

        val result = temperature._c.`°C`

        assertThat(result).isEqualTo(ValueWithUnit(200.0, "c", "°C"))
    }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(200.3, "", "K") to 200.3.K,
            ValueWithUnit(2.3, "k", "Celsius") to 2.3._k.Celsius,
            ValueWithUnit(2.3, "m", "°C") to 2.3._m.Celsius,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Temperature.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }
}
