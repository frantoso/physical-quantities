package io.github.frantoso.physicalquantities.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import javax.naming.InvalidNameException

class ExceptionsTest {
    private val testException = InvalidNameException()

    @TestFactory
    fun `initialization of NoSuchPrefixException`() =
        listOf(
            NoSuchPrefixException() to (null to null),
            NoSuchPrefixException(message = "test") to ("test" to null),
            NoSuchPrefixException(cause = testException) to (null to testException),
            NoSuchPrefixException(message = "test", cause = testException) to ("test" to testException),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input.message).isEqualTo(expected.first)
                assertThat(input.cause).isEqualTo(expected.second)
            }
        }

    @TestFactory
    fun `initialization of NoSuchUnitException`() =
        listOf(
            NoSuchUnitException() to (null to null),
            NoSuchUnitException(message = "test") to ("test" to null),
            NoSuchUnitException(cause = testException) to (null to testException),
            NoSuchUnitException(message = "test", cause = testException) to ("test" to testException),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                assertThat(input.message).isEqualTo(expected.first)
                assertThat(input.cause).isEqualTo(expected.second)
            }
        }
}
