package io.github.frantoso.physicalquantities.nonquantity

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._c
import io.github.frantoso.physicalquantities.core._d
import io.github.frantoso.physicalquantities.core._h
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class ScaleTest {
    @Test
    fun `test initialisation`() {
        val scale = Scale.fromNumber(24)

        assertThat(scale.scale).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val scale = -(24.sc) // base class calls createFromValue()

        assertThat(scale.scale).isEqualTo((-24).toRawType())
    }

    @TestFactory
    fun `test initialisation from literal`() =
        listOf(
            24.sc to 24,
            60._h.sc to 6000,
            60.percent to 0.6,
            60.permille to 0.06,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected from input: $input") {
                assertThat(input.scale.compareTo(expected.toRawType())).isEqualTo(0)
            }
        }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(42, "", "sc") to 42.sc,
            ValueWithUnit(42, "", "%") to 0.42.sc,
            ValueWithUnit(42, "", "percent") to 0.42.sc,
            ValueWithUnit(2.3, "k", "%") to 23.sc,
            ValueWithUnit(1800, "", "‰") to 1.8.sc,
            ValueWithUnit(1800, "", "permille") to 1.8.sc,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Scale.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to scaled quantity`() =
        listOf(
            42.sc._k to ScaledQuantity(42.sc, 0.001, "k"),
            42.percent._c to ScaledQuantity(0.42.sc, 100, "c"),
            42.permille._k to ScaledQuantity(0.042.sc, 0.001, "k"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit`() =
        listOf(
            32.sc.sc to ValueWithUnit(32, "", "sc"),
            42.sc._m.sc to ValueWithUnit(42000, "m", "sc"),
            3.2.sc.percent to ValueWithUnit(320, "", "%"),
            3.2.sc.permille to ValueWithUnit(3200, "", "‰"),
            3.2.sc._d.percent to ValueWithUnit(3200, "d", "%"),
            3.2.sc._d.permille to ValueWithUnit(32000, "d", "‰"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected from input: $input") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
