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

class VolumeTest {
    @Test
    fun `test initialisation`() {
        val volume = Volume.fromCubicMeter(24)

        assertThat(volume.cubicMeter).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val volume = -(24.m3) // base class calls createFromValue()

        assertThat(volume.cubicMeter).isEqualTo((-24).toRawType())
    }

    @TestFactory
    fun `test initialisation from literal`() =
        listOf(
            24.l to 0.024,
            60._h.l to 6,
            60.m3 to 60,
            60._m.m3 to 0.06,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected from input: $input") {
                assertThat(input.cubicMeter.compareTo(expected.toRawType())).isEqualTo(0)
            }
        }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(42, "", "l") to 42.l,
            ValueWithUnit(42, "", "m³") to 42000.l,
            ValueWithUnit(2.3, "k", "l") to 2.3._k.l,
            ValueWithUnit(1800, "m", "l") to 1.8.l,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Volume.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to scaled quantity`() =
        listOf(
            42.l._m to ScaledQuantity(42.l, 1000, "m"),
            60.l._k to ScaledQuantity(60.l, 0.001, "k"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit`() =
        listOf(
            32.l.l to ValueWithUnit(32, "", "l"),
            42.l._m.l to ValueWithUnit(42000, "m", "l"),
            3200.l.m3 to ValueWithUnit(3.2, "", "m³"),
            3200.l._m.m3 to ValueWithUnit(3200, "m", "m³"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
