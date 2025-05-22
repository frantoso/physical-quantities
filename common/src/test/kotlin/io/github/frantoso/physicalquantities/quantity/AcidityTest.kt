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

class AcidityTest {
    @Test
    fun `test initialisation`() {
        val volume = Acidity.fromPh(24)

        assertThat(volume.phValue).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val volume = -(24.pH) // base class calls createFromValue()

        assertThat(volume.phValue).isEqualTo((-24).toRawType())
    }

    @TestFactory
    fun `test initialisation from literal`() =
        listOf(
            24.pH to 24,
            60._h.pH to 6000,
            60._m.pH to 0.06,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input.phValue.compareTo(expected.toRawType())).isEqualTo(0)
            }
        }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(42, "", "pH") to 42.pH,
            ValueWithUnit(2.3, "k", "pH") to 2.3._k.pH,
            ValueWithUnit(1800, "m", "pH") to 1.8.pH,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Acidity.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to scaled quantity`() =
        listOf(
            42.pH._m to ScaledQuantity(42.pH, 1000, "m"),
            60.pH._k to ScaledQuantity(60.pH, 0.001, "k"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit`() =
        listOf(
            32.pH.pH to ValueWithUnit(32, "", "pH"),
            42.pH._m.pH to ValueWithUnit(42000, "m", "pH"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
