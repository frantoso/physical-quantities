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

class AmountOfSubstanceTest {
    @Test
    fun `test initialisation`() {
        val volume = AmountOfSubstance.fromMole(24)

        assertThat(volume.mole).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val volume = -(24.mol) // base class calls createFromValue()

        assertThat(volume.mole).isEqualTo((-24).toRawType())
    }

    @TestFactory
    fun `test initialisation from literal`() =
        listOf(
            24.mol to 24,
            60._h.mol to 6000,
            60._m.mol to 0.06,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input.mole.compareTo(expected.toRawType())).isEqualTo(0)
            }
        }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(42, "", "mol") to 42.mol,
            ValueWithUnit(2.3, "k", "mol") to 2.3._k.mol,
            ValueWithUnit(1800, "m", "mol") to 1.8.mol,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = AmountOfSubstance.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to scaled quantity`() =
        listOf(
            42.mol._m to ScaledQuantity(42.mol, 1000, "m"),
            60.mol._k to ScaledQuantity(60.mol, 0.001, "k"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `to value with unit`() =
        listOf(
            32.mol.mol to ValueWithUnit(32, "", "mol"),
            42.mol._m.mol to ValueWithUnit(42000, "m", "mol"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
