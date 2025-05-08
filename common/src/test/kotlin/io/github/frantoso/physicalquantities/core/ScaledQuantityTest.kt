package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class ScaledQuantityTest {
    class TestQuantity(
        value: Number,
    ) : QuantityBase(value) {
        override fun copy(): QuantityBase = TestQuantity(value)
    }

    class OtherTestQuantity(
        value: Number,
    ) : QuantityBase(value) {
        override fun copy(): QuantityBase = OtherTestQuantity(value)
    }

    @Test
    fun `tests initialization`() {
        val scaledQuantity = ScaledQuantity(TestQuantity(42), 0.001, "m")

        assertThat(scaledQuantity.quantity).isEqualTo(TestQuantity(42))
        assertThat(scaledQuantity.scaleFactor).isEqualTo(0.001.toRawType())
        assertThat(scaledQuantity.symbolPrefix).isEqualTo("m")
    }

    @TestFactory
    fun `test equals`() =
        listOf(
            ScaledQuantity(TestQuantity(42), 0.001, "m") to true,
            ScaledQuantity(TestQuantity(42.1), 0.001, "m") to false,
            ScaledQuantity(TestQuantity(42), 0.001, "M") to false,
            ScaledQuantity(OtherTestQuantity(42), 0.001, "m") to false,
            null to false,
        ).mapIndexed { index, (other, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val value = ScaledQuantity(TestQuantity(42), 0.001, "m")

                assertThat(value == other).isEqualTo(expected)
                assertThat(value != other).isNotEqualTo(expected)
            }
        }

    @Test
    fun `test hashCode same values`() {
        val hash1 = ScaledQuantity(TestQuantity(42), 0.001, "m").hashCode()
        val hash2 = ScaledQuantity(OtherTestQuantity(42), 0.001, "m").hashCode()

        assertThat(hash1).isEqualTo(hash2)
    }

    @Test
    fun `test hashCode different values`() {
        val hash1 = ScaledQuantity(TestQuantity(42), 0.001, "m").hashCode()
        val hash2 = ScaledQuantity(TestQuantity(43), 0.001, "m").hashCode()

        assertThat(hash1).isNotEqualTo(hash2)
    }
}
