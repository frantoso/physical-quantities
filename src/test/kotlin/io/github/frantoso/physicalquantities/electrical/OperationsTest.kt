package io.github.frantoso.physicalquantities.electrical

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

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
}
