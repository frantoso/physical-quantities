package io.github.frantoso.physicalquantities.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class PrefixTest {
    @TestFactory
    fun `toPrefix with valid names`() =
        listOf(
            "m" to Prefix.MILLI,
            "k" to Prefix.KILO,
            "G" to Prefix.GIGA,
            "milli" to Prefix.MILLI,
            "Kilo" to Prefix.KILO,
            "GIGA" to Prefix.GIGA,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = input.toPrefix()

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `toPrefix with invalid names`() =
        listOf(
            "l" to "Unknown prefix l",
            "Foo" to "Unknown prefix Foo",
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThatThrownBy { input.toPrefix() }.isInstanceOf(NoSuchPrefixException::class.java).message().isEqualTo(
                    expected,
                )
            }
        }
}
