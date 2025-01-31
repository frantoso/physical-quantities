package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._k
import io.github.frantoso.physicalquantities.core._m
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class EnergyTest {
    @Test
    fun `test initialisation`() {
        val energy = Energy.fromJoule(24)

        assertThat(energy.joule).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal (J)`() {
        val energy = 24.J

        assertThat(energy.joule).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal (Ws)`() {
        val energy = 24.Ws

        assertThat(energy.joule).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal (Wh)`() {
        val energy = 24.Wh

        assertThat(energy.joule).isEqualTo(86_400.0)
    }

    @Test
    fun `test createFromValue`() {
        val energy = -(24.J) // base class calls createFromValue()

        assertThat(energy.joule).isEqualTo(-24.0)
    }

    @Test
    fun `to value with unit`() {
        val energy = 7200.J

        val resultJ = energy.J
        val resultWs = energy.Ws
        val resultWh = energy.Wh

        assertThat(resultJ).isEqualTo(ValueWithUnit(7_200.0, "", "J"))
        assertThat(resultWs).isEqualTo(ValueWithUnit(7_200.0, "", "Ws"))
        assertThat(resultWh).isEqualTo(ValueWithUnit(2.0, "", "Wh"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val energy = 10_800.J

        val resultJ = energy._m.J
        val resultWs = energy._m.Ws
        val resultWh = energy._k.Wh

        assertThat(resultJ).isEqualTo(ValueWithUnit(10_800_000.0, "m", "J"))
        assertThat(resultWs).isEqualTo(ValueWithUnit(10_800_000.0, "m", "Ws"))
        assertThat(resultWh).isEqualTo(ValueWithUnit(0.003, "k", "Wh"))
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

                assertThat(result).isEqualTo(expected)
            }
        }
}
