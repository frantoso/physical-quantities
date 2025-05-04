package io.github.frantoso.physicalquantities.quantity

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
}
