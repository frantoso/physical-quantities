package io.github.frantoso.physicalquantities.utils

import io.github.frantoso.physicalquantities.electrical.A
import io.github.frantoso.physicalquantities.electrical.V
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class ExtensionsTest {
    @TestFactory
    fun `test eqByPrecision`() =
        listOf(
            (1.A to 1.0009.A) to true,
            (1.A to 0.9991.A) to true,
            (1.V to 1.0011.V) to false,
            (1.V to 0.999.V) to false,
            (1.A to 1.0009.V) to false,
            (1.A to 0.9991.V) to false,
            (1.A to null) to false,
            (null to 1.0009.A) to false,
            (null to null) to true,
        ).mapIndexed { index, (operators, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val (left, right) = operators

                val result = left eqByPrecision right

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `test eqByPrecision with custom precision`() =
        listOf(
            (1.A to 1.09.A) to true,
            (1.A to 0.91.A) to true,
            (1.V to 1.1.V) to false,
            (1.V to 0.89.V) to false,
        ).mapIndexed { index, (operators, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                setDefaultPrecisionFraction(0.1.toRawType())
                val (left, right) = operators

                val result = left eqByPrecision right

                resetDefaultPrecisionFraction()
                assertThat(result).isEqualTo(expected)
            }
        }
}
