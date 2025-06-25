package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.time.Duration.Companion.seconds

class OperationsTest {
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
    fun `tests molar concentration related operations`() =
        listOf(
            { 12.mol / 2.l } to 6.molPerL,
            { 3.molPerM3 * 3.l } to 0.009.mol,
            { 8.mol / 2.M } to 4.l,
            { 8.mol / 4.l } to 2.molPerL,
            { 2.M * 4.l } to 8.mol,
        ).mapIndexed { index, (functionToTest, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = functionToTest()

                assertThat(result.equalsByPrecision(expected, 0.000000000001.toRawType())).isTrue
            }
        }

    @TestFactory
    fun `tests flow rate related operations`() =
        listOf(
            { 2.m3s * 4.seconds } to 8.m3,
            { 7.seconds * 3.m3s } to 21.m3,
            { 8.m3 / 2.seconds } to 4.m3s,
            { 21.m3 / 7.m3s } to 3.seconds,
        ).mapIndexed { index, (functionToTest, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = functionToTest()

                assertThat(result).isEqualTo(expected)
            }
        }
}
