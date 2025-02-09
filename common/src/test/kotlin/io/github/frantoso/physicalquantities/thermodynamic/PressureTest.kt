package io.github.frantoso.physicalquantities.thermodynamic

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.testUtils.STANDARD_OFFSET
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class PressureTest {
    @Test
    fun `test initialisation`() {
        val pressure = Pressure.fromPascal(24)

        assertThat(pressure.pascal).isEqualTo(24.toRawType())
    }

    @Test
    fun `test initialisation from literal (Pa)`() {
        val pressure = 24.Pa

        assertThat(pressure.pascal).isEqualTo(24.toRawType())
    }

    @Test
    fun `test initialisation from literal (bar)`() {
        val pressure = 2.3.bar

        assertThat(pressure.pascal).isCloseTo(230_000.0.toRawType(), STANDARD_OFFSET)
    }

    @Test
    fun `test createFromValue`() {
        val pressure = -(24.Pa) // base class calls createFromValue()

        assertThat(pressure.pascal).isEqualTo((-24).toRawType())
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

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(2.3, "k", "Pa") to 2.3._k.Pa,
            ValueWithUnit(2.3, "m", "bar") to 2.3._m.bar,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Pressure.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }
}
