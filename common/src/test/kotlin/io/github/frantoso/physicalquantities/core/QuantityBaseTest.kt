package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.electrical.A
import io.github.frantoso.physicalquantities.electrical.C
import io.github.frantoso.physicalquantities.electrical.O
import io.github.frantoso.physicalquantities.electrical.V
import io.github.frantoso.physicalquantities.quantity.Hz
import io.github.frantoso.physicalquantities.quantity.J
import io.github.frantoso.physicalquantities.quantity.W
import io.github.frantoso.physicalquantities.quantity.ft
import io.github.frantoso.physicalquantities.quantity.inch
import io.github.frantoso.physicalquantities.quantity.l
import io.github.frantoso.physicalquantities.quantity.m
import io.github.frantoso.physicalquantities.quantity.mile
import io.github.frantoso.physicalquantities.quantity.mol
import io.github.frantoso.physicalquantities.quantity.molPerM3
import io.github.frantoso.physicalquantities.thermodynamic.Celsius
import io.github.frantoso.physicalquantities.thermodynamic.Degrees
import io.github.frantoso.physicalquantities.thermodynamic.K
import io.github.frantoso.physicalquantities.thermodynamic.Pa
import io.github.frantoso.physicalquantities.thermodynamic.bar
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class TestBaseQuantity(
    value: Number,
) : QuantityBase(value) {
    override fun copy(): QuantityBase = TestBaseQuantity(value)
}

class OtherTestBaseQuantity(
    value: Number,
) : QuantityBase(value) {
    override fun copy(): QuantityBase = OtherTestBaseQuantity(value)
}

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

    @Test
    fun `test rawValueNotForNormalUsage is equals to value`() {
        val value1 = TestBaseQuantity(20.3)
        val value2 = TestBaseQuantity(-20.6)

        assertThat(value1.rawValueNotForNormalUsage).isEqualTo(value1.value)
        assertThat(value2.rawValueNotForNormalUsage).isEqualTo(value2.value)
    }

    @TestFactory
    fun `copies a quantity`() =
        listOf(
            2.3._m.C,
            2.3._m.A,
            2.3._m.O,
            2.3._m.V,
            2.3._k.mol,
            .8.mol,
            2.3._k.J,
            2.3._k.J,
            (2.3 * 3_600)._k.J,
            2.3._M.Hz,
            2.3._m.m,
            2.3.inch,
            2.3.ft,
            2.3._k.m,
            2.3.mile,
            42.molPerM3,
            2.3._k.molPerM3,
            1.8.molPerM3,
            42000.molPerM3,
            2300._k.molPerM3,
            1800.molPerM3,
            2.3._k.molPerM3,
            2.3._M.molPerM3,
            1800.molPerM3,
            2.3._m.W,
            4200.l,
            2.3._k.l,
            1800._m.l,
            2.3._k.Pa,
            2.3._m.bar,
            200.3.K,
            2.3._k.Celsius,
            2.3._m.Celsius,
            25.Degrees,
        ).mapIndexed { index, input ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $input") {
                val result = input.copy()

                assertThat(result).isEqualTo(input)
                assertThat(result).isNotSameAs(input)
            }
        }
}
