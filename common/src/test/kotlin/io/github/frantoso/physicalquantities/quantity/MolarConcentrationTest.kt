package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._M
import io.github.frantoso.physicalquantities.core._h
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class MolarConcentrationTest {
    @Test
    fun `test initialisation`() {
        val volume = MolarConcentration.fromMolePerCubicMeters(24)

        assertThat(volume.molePerCubicMeters).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val volume = -(24.molPerM3) // base class calls createFromValue()

        assertThat(volume.molePerCubicMeters).isEqualTo((-24).toRawType())
    }

    @TestFactory
    fun `test initialisation from literal`() =
        listOf(
            24.molPerM3 to 24,
            60._h.molPerM3 to 6000,
            60._m.molPerM3 to 0.06,
            24.molPerL to 24000,
            60._h.molPerL to 6000000,
            60._m.molPerL to 60,
            24.M to 24000,
            60._h.M to 6000000,
            60._m.M to 60,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input.molePerCubicMeters.compareTo(expected.toRawType())).isEqualTo(0)
            }
        }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(42, "", "mol/m³") to 42.molPerM3,
            ValueWithUnit(2.3, "k", "mol/m³") to 2.3._k.molPerM3,
            ValueWithUnit(1800, "m", "mol/m³") to 1.8.molPerM3,
            ValueWithUnit(42, "", "mol/l") to 42000.molPerM3,
            ValueWithUnit(2.3, "k", "mol/l") to 2300._k.molPerM3,
            ValueWithUnit(1800, "m", "mol/l") to 1800.molPerM3,
            ValueWithUnit(42, "", "M") to 42._k.molPerM3,
            ValueWithUnit(2.3, "k", "M") to 2.3._M.molPerM3,
            ValueWithUnit(1800, "m", "M") to 1800.molPerM3,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = MolarConcentration.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to scaled quantity`() =
        listOf(
            42.molPerM3._m to ScaledQuantity(42.molPerM3, 1000, "m"),
            60.molPerM3._k to ScaledQuantity(60.molPerM3, 0.001, "k"),
            42.molPerL._m to ScaledQuantity(42.molPerL, 1000, "m"),
            60.molPerL._k to ScaledQuantity(60.molPerL, 0.001, "k"),
            42.M._m to ScaledQuantity(42.M, 1000, "m"),
            60.M._k to ScaledQuantity(60.M, 0.001, "k"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit`() =
        listOf(
            32.M.molPerM3 to ValueWithUnit(32000, "", "mol/m³"),
            42.M._m.molPerM3 to ValueWithUnit(42000000, "m", "mol/m³"),
            32.M.molPerL to ValueWithUnit(32, "", "mol/l"),
            42.M._m.molPerL to ValueWithUnit(42000, "m", "mol/l"),
            32.M.M to ValueWithUnit(32, "", "M"),
            42.M._m.M to ValueWithUnit(42000, "m", "M"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
