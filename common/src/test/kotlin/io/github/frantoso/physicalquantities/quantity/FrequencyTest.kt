package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._M
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class FrequencyTest {
    @Test
    fun `test initialisation`() {
        val frequency = Frequency.fromHertz(24)

        assertThat(frequency.hertz).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val frequency = -(24.Hz) // base class calls createFromValue()

        assertThat(frequency.hertz).isEqualTo((-24).toRawType())
    }

    @TestFactory
    fun `test initialisation from literal`() =
        listOf(
            24.Hz to 24,
            60.rpm to 1,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input.hertz).isEqualTo(expected.toRawType())
            }
        }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(2.3, "M", "Hz") to 2.3._M.Hz,
            ValueWithUnit(1800, "", "rpm") to 30.Hz,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Frequency.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to scaled quantity`() =
        listOf(
            42.Hz._m to ScaledQuantity(42.Hz, 1000, "m"),
            60.Hz._k to ScaledQuantity(60.Hz, 0.001, "k"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit`() =
        listOf(
            32.Hz.Hz to ValueWithUnit(32, "", "Hz"),
            2.Hz.rpm to ValueWithUnit(120, "", "rpm"),
            360.rpm.Hz to ValueWithUnit(6, "", "Hz"),
            360.rpm.rpm to ValueWithUnit(360, "", "rpm"),
            42.Hz._m.Hz to ValueWithUnit(42000, "m", "Hz"),
            60.Hz._k.rpm to ValueWithUnit(3.6, "k", "rpm"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
