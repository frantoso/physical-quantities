package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class TestBaseQuantity(
    value: Number,
) : QuantityBase(value)

class OtherTestBaseQuantity(
    value: Number,
) : QuantityBase(value)

class QuantityBaseTest {
    @TestFactory
    fun `test equals`() =
        listOf(
            TestBaseQuantity(42.0) to true,
            TestBaseQuantity(2.0) to false,
            OtherTestBaseQuantity(42.0) to false,
            null to false,
        ).mapIndexed { index, (other, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val value1 = TestBaseQuantity(42)

                assertThat(value1 == other).isEqualTo(expected)
                assertThat(value1 != other).isNotEqualTo(expected)
            }
        }

    @TestFactory
    fun `test equalsByPrecision`() =
        listOf(
            TestBaseQuantity(42.0) to true,
            TestBaseQuantity(42.0001) to true,
            TestBaseQuantity(41.9991) to true,
            OtherTestBaseQuantity(42.0) to false,
            null to false,
        ).mapIndexed { index, (other, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val value1 = TestBaseQuantity(42)

                assertThat(value1.equalsByPrecision(other)).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `test hashCode`() =
        listOf(
            TestBaseQuantity(42.0) to 42.toRawType().hashCode(),
            TestBaseQuantity(42.1) to 42.1.toRawType().hashCode(),
            TestBaseQuantity(2.0) to 2.toRawType().hashCode(),
            OtherTestBaseQuantity(42) to 42.toRawType().hashCode(),
            TestBaseQuantity(42) to 42.toRawType().hashCode(),
            TestBaseQuantity(42) to TestBaseQuantity(42).hashCode(),
        ).mapIndexed { index, (value, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - hash code is $expected") {
                assertThat(value.hashCode()).isEqualTo(expected)
            }
        }

    @Test
    fun `test sign`() {
        val value1 = TestBaseQuantity(20.3)
        val value2 = TestBaseQuantity(-20.6)

        val result1 = value1.sign
        val result2 = value2.sign

        assertThat(result1).isEqualTo(1)
        assertThat(result2).isEqualTo(-1)
    }

    @Test
    fun `test toString`() {
        val value1 = TestBaseQuantity(20.3)
        val value2 = TestBaseQuantity(-20.6)

        val result1 = value1.toString()
        val result2 = value2.toString()

        assertThat(result1).isEqualTo("20.3")
        assertThat(result2).isEqualTo("-20.6")
    }
}
