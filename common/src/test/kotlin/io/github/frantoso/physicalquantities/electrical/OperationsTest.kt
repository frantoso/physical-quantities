package io.github.frantoso.physicalquantities.electrical

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test
import kotlin.time.Duration.Companion.seconds

class OperationsTest {
    @TestFactory
    fun `tests power related operations`() =
        listOf(
            { 7.V * 3.A } to 21.W,
            { 2.A * 4.V } to 8.W,
            { 7.W / 2.A } to 3.5.V,
            { 9.W / 3.V } to 3.A,
        ).mapIndexed { index, (functionToTest, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = functionToTest()

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `tests resistance related operations`() =
        listOf(
            { 2.A * 4.O } to 8.V,
            { 7.O * 3.A } to 21.V,
            { 8.V / 2.A } to 4.O,
            { 21.V / 7.O } to 3.A,
        ).mapIndexed { index, (functionToTest, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = functionToTest()

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `tests energy related operations`() =
        listOf(
            { 2.W * 4.seconds } to 8.J,
            { 7.seconds * 3.W } to 21.J,
            { 8.J / 2.seconds } to 4.W,
            { 21.J / 7.W } to 3.seconds,
        ).mapIndexed { index, (functionToTest, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = functionToTest()

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `tests charge related operations`() =
        listOf(
            { 2.A * 4.seconds } to 8.C,
            { 7.seconds * 3.A } to 21.C,
            { 8.C / 2.A } to 4.seconds,
            { 21.C / 7.seconds } to 3.A,
        ).mapIndexed { index, (functionToTest, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = functionToTest()

                assertThat(result).isEqualTo(expected)
            }
        }

    @Test
    fun `calculation with unlimited decimal places does not throw exception`() {
        assertDoesNotThrow { 2.W / 3.A }
    }
}
