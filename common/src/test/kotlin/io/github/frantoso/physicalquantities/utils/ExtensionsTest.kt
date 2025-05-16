package io.github.frantoso.physicalquantities.utils

import io.github.frantoso.physicalquantities.core.QuantityBase
import io.github.frantoso.physicalquantities.electrical.A
import io.github.frantoso.physicalquantities.electrical.V
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

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

    data class TestData(
        val value: QuantityBase,
        val min: QuantityBase?,
        val max: QuantityBase?,
    )

    @TestFactory
    fun `tests whether a quantity is inside a range`() =
        listOf(
            TestData(1.A, 0.8.A, 1.09.A) to true,
            TestData(1.A, null, 1.09.A) to true,
            TestData(1.A, 0.8.A, null) to true,
            TestData(1.A, null, null) to true,
            TestData(1.A, 1.8.A, 1.9.A) to false,
            TestData(1.A, 0.8.A, 0.9.A) to false,
            TestData(1.A, null, 0.9.A) to false,
            TestData(1.A, 1.1.A, null) to false,
            TestData(1.V, 0.8.A, 1.09.A) to false,
            TestData(1.A, 0.8.V, 1.09.A) to false,
            TestData(1.A, 0.8.A, 1.09.V) to false,
        ).mapIndexed { index, (data, expected) ->
            DynamicTest.dynamicTest(
                "${"%02d".format(index)} - ${data.value} is ${if (expected) "" else "not "} inside ${data.min} and ${data.max}",
            ) {
                val result = data.value.isInRange(data.min, data.max)

                assertThat(result).isEqualTo(expected)
            }
        }

    @Test
    fun `call isInRange with default values`() {
        val result = 1.A.isInRange()

        assertThat(result).isTrue
    }

    @Test
    fun `call isInRange with default min value`() {
        val result1 = 1.A.isInRange(max = 1.09.A)
        val result2 = 1.A.isInRange(max = 0.09.A)

        assertThat(result1).isTrue
        assertThat(result2).isFalse
    }

    @Test
    fun `call isInRange with default max value`() {
        val result1 = 1.A.isInRange(min = 0.8.A)
        val result2 = 1.A.isInRange(min = 1.8.A)

        assertThat(result1).isTrue
        assertThat(result2).isFalse
    }
}
