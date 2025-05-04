package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class EnergyTest {
    @Test
    fun `test initialisation`() {
        val energy = Energy.fromJoule(24)

        Assertions.assertThat(energy.joule).isEqualTo(24.toRawType())
    }

    @Test
    fun `test initialisation from literal (J)`() {
        val energy = 24.J

        Assertions.assertThat(energy.joule).isEqualTo(24.toRawType())
    }

    @Test
    fun `test initialisation from literal (Ws)`() {
        val energy = 24.Ws

        Assertions.assertThat(energy.joule).isEqualTo(24.toRawType())
    }

    @Test
    fun `test initialisation from literal (Wh)`() {
        val energy = 24.Wh

        Assertions.assertThat(energy.joule).isEqualTo(86_400.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val energy = -(24.J) // base class calls createFromValue()

        Assertions.assertThat(energy.joule).isEqualTo((-24).toRawType())
    }

    @Test
    fun `to value with unit`() {
        val energy = 7200.J

        val resultJ = energy.J
        val resultWs = energy.Ws
        val resultWh = energy.Wh

        Assertions.assertThat(resultJ).isEqualTo(ValueWithUnit(7_200.0, "", "J"))
        Assertions.assertThat(resultWs).isEqualTo(ValueWithUnit(7_200.0, "", "Ws"))
        Assertions.assertThat(resultWh).isEqualTo(ValueWithUnit(2.0, "", "Wh"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val energy = 10_800.J

        val resultJ = energy._m.J
        val resultWs = energy._m.Ws
        val resultWh = energy._k.Wh

        Assertions.assertThat(resultJ).isEqualTo(ValueWithUnit(10_800_000.0, "m", "J"))
        Assertions.assertThat(resultWs).isEqualTo(ValueWithUnit(10_800_000.0, "m", "Ws"))
        Assertions.assertThat(resultWh).isEqualTo(ValueWithUnit(0.003, "k", "Wh"))
    }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(2.3, "k", "J") to 2.3._k.J,
            ValueWithUnit(2.3, "k", "Ws") to 2.3._k.J,
            ValueWithUnit(2.3, "k", "Wh") to (2.3 * 3_600)._k.J,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Energy.fromValueWithUnit(input)

                Assertions.assertThat(result).isEqualTo(expected)
            }
        }
}
