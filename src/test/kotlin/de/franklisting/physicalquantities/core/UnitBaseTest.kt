package de.franklisting.physicalquantities.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class TestBaseUnit(value: Number) : UnitBase(value.toDouble())

class OtherTestBaseUnit(value: Double) : UnitBase(value)

class UnitBaseTest {
    @TestFactory
    fun `test equals`() =
        listOf(
            TestBaseUnit(42.0) to true,
            TestBaseUnit(2.0) to false,
            OtherTestBaseUnit(42.0) to false,
            null to false,
        ).mapIndexed { index, (other, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val value1 = TestBaseUnit(42)

                assertThat(value1 == other).isEqualTo(expected)
                assertThat(value1 != other).isNotEqualTo(expected)
            }
        }

    @TestFactory
    fun `test hashCode`() =
        listOf(
            TestBaseUnit(42.0) to 42.0.hashCode(),
            TestBaseUnit(2.0) to 2.0.hashCode(),
            OtherTestBaseUnit(42.0) to 142.0.hashCode(),
        ).mapIndexed { index, (value, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - hash code is $expected") {
                assertThat(value.hashCode()).isEqualTo(expected)
            }
        }

    @Test
    fun `test sign`() {
        val value1 = TestBaseUnit(20.3)
        val value2 = TestBaseUnit(-20.6)

        val result1 = value1.sign
        val result2 = value2.sign

        assertThat(result1).isEqualTo(1.0)
        assertThat(result2).isEqualTo(-1.0)
    }
}
