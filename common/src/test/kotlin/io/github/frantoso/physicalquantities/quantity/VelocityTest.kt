package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class VelocityTest {
    @Test
    fun `test initialisation`() {
        val velocity = Velocity.fromMetersPerSecond(24)

        assertThat(velocity.metersPerSecond).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val velocity = -(24.mPerS) // base class calls createFromValue()

        assertThat(velocity.metersPerSecond).isEqualTo((-24).toRawType())
    }

    @TestFactory
    fun `test initialisation from literal`() =
        listOf(
            24.mPerS to 24,
            60._m.mPerS to 0.06,
            36.kmPerH to 10,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input.metersPerSecond.compareTo(expected.toRawType())).isEqualTo(0)
            }
        }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(42, "", "m/s") to 42.mPerS,
            ValueWithUnit(2.3, "k", "m/s") to 2.3._k.mPerS,
            ValueWithUnit(1800, "m", "m/s") to 1.8.mPerS,
            ValueWithUnit(36, "", "km/h") to 10.mPerS,
            ValueWithUnit(3.6, "k", "km/h") to 1000.mPerS,
            ValueWithUnit(3600, "m", "km/h") to 1.mPerS,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Velocity.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to scaled quantity`() =
        listOf(
            42.mPerS._m to ScaledQuantity(42.mPerS, 1000, "m"),
            60.mPerS._k to ScaledQuantity(60.mPerS, 0.001, "k"),
            42.kmPerH._m to ScaledQuantity(42.kmPerH, 1000, "m"),
            60.kmPerH._k to ScaledQuantity(60.kmPerH, 0.001, "k"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit`() =
        listOf(
            10.mPerS.mPerS to ValueWithUnit(10, "", "m/s"),
            42.mPerS._m.mPerS to ValueWithUnit(42000, "m", "m/s"),
            10.mPerS.kmPerH to ValueWithUnit(36, "", "km/h"),
            72.mPerS._m.kmPerH to ValueWithUnit(259200, "m", "km/h"),
            36.kmPerH.mPerS to ValueWithUnit(10, "", "m/s"),
            36.kmPerH._m.mPerS to ValueWithUnit(10000, "m", "m/s"),
            36.kmPerH.kmPerH to ValueWithUnit(36, "", "km/h"),
            36.kmPerH._m.kmPerH to ValueWithUnit(36000, "m", "km/h"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
