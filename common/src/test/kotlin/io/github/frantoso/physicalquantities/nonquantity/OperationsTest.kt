package io.github.frantoso.physicalquantities.nonquantity

import io.github.frantoso.physicalquantities.electrical.A
import io.github.frantoso.physicalquantities.electrical.V
import io.github.frantoso.physicalquantities.quantity.W
import io.github.frantoso.physicalquantities.quantity.l
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class OperationsTest {
    @TestFactory
    fun `tests scale related operations`() =
        listOf(
            { 100.l * 15.percent } to 15.l,
            { 7.V * 3.sc } to 21.V,
            { 2.A * 4.percent } to 0.08.A,
            { 3.sc * 7.V } to 21.V,
            { 4.percent * 2.A } to 0.08.A,
            { 7.W / 2.sc } to 3.5.W,
            { 9.A / 3.percent } to 300.A,
        ).mapIndexed { index, (functionToTest, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = functionToTest()

                assertThat(result).isEqualTo(expected)
            }
        }
}
