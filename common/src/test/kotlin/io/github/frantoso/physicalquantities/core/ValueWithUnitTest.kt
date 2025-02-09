package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class ValueWithUnitTest {
    @Test
    fun `tests initialization`() {
        val valueWithUnit = ValueWithUnit(42, "m", "A")

        assertThat(valueWithUnit.value).isEqualTo(42.toRawType())
        assertThat(valueWithUnit.symbolPrefix).isEqualTo("m")
        assertThat(valueWithUnit.symbolUnit).isEqualTo("A")
    }

    @TestFactory
    fun `test equals`() =
        listOf(
            ValueWithUnit(42, "m", "V") to true,
            ValueWithUnit(42.0, "m", "V") to true,
            ValueWithUnit(42.1, "m", "V") to false,
            ValueWithUnit(42, "M", "V") to false,
            ValueWithUnit(42, "m", "A") to false,
            null to false,
        ).mapIndexed { index, (other, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val value = ValueWithUnit(42, "m", "V")

                assertThat(value == other).isEqualTo(expected)
                assertThat(value != other).isNotEqualTo(expected)
            }
        }

    @Test
    fun `test hashCode same values`() {
        val hash1 = ValueWithUnit(42, "m", "V").hashCode()
        val hash2 = ValueWithUnit(42, "m", "V").hashCode()

        assertThat(hash1).isEqualTo(hash2)
    }

    @Test
    fun `test hashCode same values different initialization`() {
        val hash1 = ValueWithUnit(42, "m", "V").hashCode()
        val hash2 = ValueWithUnit(42.0, "m", "V").hashCode()

        assertThat(hash1).isEqualTo(hash2)
    }

    @Test
    fun `test hashCode different values`() {
        val hash1 = ValueWithUnit(42, "m", "V").hashCode()
        val hash2 = ValueWithUnit(42.1, "m", "V").hashCode()

        assertThat(hash1).isNotEqualTo(hash2)
    }
}
