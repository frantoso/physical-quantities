package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.core.centi
import io.github.frantoso.physicalquantities.core.deci
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class LengthTest {
    @Test
    fun `test initialisation`() {
        val length = Length.fromMeter(24)

        assertThat(length.meter).isEqualTo(24.toRawType())
    }

    @Test
    fun `test initialisation from literal`() {
        val length = 24.m

        assertThat(length.meter).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val length = -(24.m) // base class calls createFromValue()

        assertThat(length.meter).isEqualTo((-24).toRawType())
    }

    @Test
    fun `to value with unit`() {
        val length = 32.m

        val result = length.m

        assertThat(result).isEqualTo(ValueWithUnit(32, "", "m"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val length = 42.m

        val result = length._m.m

        assertThat(result).isEqualTo(ValueWithUnit(42000, "m", "m"))
    }

    @Test
    fun `x-from value with unit`() {
        val input = ValueWithUnit(2.3, "m", "m")
        val result = Length.fromValueWithUnit(input)

        assertThat(result).isEqualTo(2.3._m.m)
    }

    @TestFactory
    fun `creates different length values`() =
        listOf(
            1.inch to 0.0254,
            1.ft to 0.3048,
            1.m to 1,
            1.mile to 1609.344,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input.meter).isEqualTo(expected.toRawType())
            }
        }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(2.3, "", "in") to 2.3.inch,
            ValueWithUnit(2.3, "", "ft") to 2.3.ft,
            ValueWithUnit(2.3, "k", "m") to 2.3._k.m,
            ValueWithUnit(2.3, "", "mile") to 2.3.mile,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Length.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit from Length`() =
        listOf(
            2.3.inch.inch to ValueWithUnit(2.3, "", "in"),
            2.3.ft.ft to ValueWithUnit(2.3, "", "ft"),
            2.3._k.m.m to ValueWithUnit(2300, "", "m"),
            2.3.mile.mile to ValueWithUnit(2.3, "", "mile"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit from scaled quantity`() =
        listOf(
            2.3.inch._m to ScaledQuantity(2.3.inch, 1000, "m"),
            2.3.ft._k to ScaledQuantity(2.3.ft, 0.001, "k"),
            2.3._k.m._m to ScaledQuantity(2300.m, 1000, "m"),
            2.3.mile.deci to ScaledQuantity(2.3.mile, 10, "d"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `from scaled quantity`() =
        listOf(
            2.3.inch._m.inch to ValueWithUnit(2300, "m", "in"),
            2.3.ft._k.ft to ValueWithUnit(0.0023, "k", "ft"),
            2000.m._k.m to ValueWithUnit(2, "k", "m"),
            23.mile.centi.mile to ValueWithUnit(2300, "c", "mile"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
