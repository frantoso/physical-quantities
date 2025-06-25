package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._h
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class FlowRateTest {
    @Test
    fun `test initialisation`() {
        val volume = FlowRate.fromCubicMetersPerSecond(24)

        assertThat(volume.cubicMetersPerSecond).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val volume = -(24.m3s) // base class calls createFromValue()

        assertThat(volume.cubicMetersPerSecond).isEqualTo((-24).toRawType())
    }

    @TestFactory
    fun `test initialisation from literal`() =
        listOf(
            24.m3s to 24,
            60._h.m3s to 6000,
            60._m.m3s to 0.06,
            60._h.ls to 6,
            60._k.ls to 60,
            24.ls to 0.024,
            60.lmin to 0.001,
            60._h.lmin to 0.1,
            60._k.lmin to 1,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input.cubicMetersPerSecond.compareTo(expected.toRawType())).isEqualTo(0)
            }
        }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(42, "", "m³/s") to 42.m3s,
            ValueWithUnit(2.3, "k", "m³/s") to 2.3._k.m3s,
            ValueWithUnit(1800, "m", "m³/s") to 1.8.m3s,
            ValueWithUnit(42, "", "l/s") to 42._m.m3s,
            ValueWithUnit(2.3, "k", "l/s") to 2.3.m3s,
            ValueWithUnit(1800, "m", "l/s") to 1.8.ls,
            ValueWithUnit(60, "", "l/min") to 1.ls,
            ValueWithUnit(2.4, "k", "l/min") to 40.ls,
            ValueWithUnit(1800, "m", "l/min") to 0.03.ls,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = FlowRate.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to scaled quantity`() =
        listOf(
            42.m3s._m to ScaledQuantity(42.m3s, 1000, "m"),
            60.m3s._k to ScaledQuantity(60.m3s, 0.001, "k"),
            42.ls._m to ScaledQuantity(42.ls, 1000, "m"),
            60.ls._k to ScaledQuantity(60.ls, 0.001, "k"),
            42.lmin._m to ScaledQuantity(42.lmin, 1000, "m"),
            60.lmin._k to ScaledQuantity(60.lmin, 0.001, "k"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit`() =
        listOf(
            32.ls.m3s to ValueWithUnit(0.032, "", "m³/s"),
            42.ls._m.m3s to ValueWithUnit(42, "m", "m³/s"),
            32.ls.ls to ValueWithUnit(32, "", "l/s"),
            42.ls._m.ls to ValueWithUnit(42000, "m", "l/s"),
            32.ls.lmin to ValueWithUnit(1920, "", "l/min"),
            42.ls._m.lmin to ValueWithUnit(2520000, "m", "l/min"),
            30.lmin.lmin to ValueWithUnit(30, "", "l/min"),
            60.lmin._m.lmin to ValueWithUnit(60000, "m", "l/min"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
