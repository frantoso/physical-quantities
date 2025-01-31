package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.core.SimpleQuantity.CreatorInfo
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class SimpleQuantityTest {
    class TestQuantity(
        value: Number,
    ) : SimpleQuantity<TestQuantity, TestQuantity>(value.toDouble(), "y") {
        override fun createFromValue(value: Number): TestQuantity = TestQuantity(value.toDouble())

        companion object {
            fun callFromValueWithUnit(
                input: ValueWithUnit,
                creators: List<CreatorInfo<TestQuantity>>,
            ): TestQuantity = fromValueWithUnit(input, creators)
        }
    }

    class TestDiff(
        value: Number,
    ) : QuantityBase(value.toDouble())

    class TestQuantityWithDiff(
        value: Number,
    ) : SimpleQuantity<TestQuantityWithDiff, TestDiff>(value.toDouble(), "z") {
        override fun createFromValue(value: Number): TestQuantityWithDiff = TestQuantityWithDiff(value.toDouble())
    }

    @Test
    fun `test properties`() {
        val quantity = TestQuantity(20.1)

        assertThat(quantity.value).isEqualTo(20.1)
        assertThat(quantity.unitSymbol).isEqualTo("y")
    }

    @Test
    fun `test greater than`() {
        val quantity1 = TestQuantity(41)
        val quantity2 = TestQuantity(42.0)

        assertThat(quantity1 < quantity2).isTrue
        assertThat(quantity1 <= quantity2).isTrue
        assertThat(quantity1 > quantity2).isFalse
        assertThat(quantity1 >= quantity2).isFalse
        assertThat(quantity2 < quantity1).isFalse
        assertThat(quantity2 <= quantity1).isFalse
        assertThat(quantity2 > quantity1).isTrue
        assertThat(quantity2 >= quantity1).isTrue
    }

    @Test
    fun `test unary minus`() {
        val quantity1 = TestQuantity(41)
        val quantity2 = -quantity1
        val quantity3 = -TestQuantity(41)

        assertThat(quantity1).isInstanceOf(TestQuantity::class.java)
        assertThat(quantity2).isInstanceOf(TestQuantity::class.java)
        assertThat(quantity3).isInstanceOf(TestQuantity::class.java)
        assertThat(quantity1).isNotEqualTo(quantity2)
        assertThat(quantity2).isEqualTo(quantity3)
        assertThat(quantity2.value).isEqualTo(-41.0)
    }

    @Test
    fun `test minus`() {
        val quantity1 = TestQuantity(20)
        val quantity2 = TestQuantity(5)

        val result = quantity1 - quantity2

        assertThat(result).isInstanceOf(TestQuantity::class.java)
        assertThat(result).isEqualTo(TestQuantity(15))
    }

    @Test
    fun `test plus`() {
        val quantity1 = TestQuantity(20)
        val quantity2 = TestQuantity(5)

        val result = quantity1 + quantity2

        assertThat(result).isInstanceOf(TestQuantity::class.java)
        assertThat(result).isEqualTo(TestQuantity(25))
    }

    @Test
    fun `test scaling`() {
        val quantity1 = TestQuantity(20)
        val scale = 2

        val resultTimes = quantity1 * scale
        val resultDiv = quantity1 / scale

        assertThat(resultTimes).isInstanceOf(TestQuantity::class.java)
        assertThat(resultDiv).isInstanceOf(TestQuantity::class.java)
        assertThat(resultTimes).isEqualTo(TestQuantity(40))
        assertThat(resultDiv).isEqualTo(TestQuantity(10))
    }

    @Test
    fun `test round`() {
        val quantity1 = TestQuantity(20.3)
        val quantity2 = TestQuantity(20.6)

        val result1 = quantity1.round()
        val result2 = quantity2.round()

        assertThat(result1).isEqualTo(TestQuantity(20))
        assertThat(result2).isEqualTo(TestQuantity(21))
    }

    @Test
    fun `test minus with diff type`() {
        val quantity1 = TestQuantityWithDiff(20)
        val quantity2 = TestDiff(5)

        val result = quantity1 - quantity2

        assertThat(result).isInstanceOf(TestQuantityWithDiff::class.java)
        assertThat(result).isEqualTo(TestQuantityWithDiff(15))
    }

    @Test
    fun `test plus diff type`() {
        val quantity1 = TestQuantityWithDiff(20)
        val quantity2 = TestDiff(5)

        val result = quantity1 + quantity2

        assertThat(result).isInstanceOf(TestQuantityWithDiff::class.java)
        assertThat(result).isEqualTo(TestQuantityWithDiff(25))
    }

    @Test
    fun `test toString`() {
        val quantity1 = TestQuantity(20.12345678)
        val quantity2 = TestQuantity(-2000.6)

        val result1 = quantity1.toString()
        val result2 = quantity2.toString()

        assertThat(result1).isEqualTo("20.123457 y")
        assertThat(result2).isEqualTo("-2,000.6 y")
    }

    @Test
    fun `test CreatorInfo`() {
        val info = CreatorInfo("y") { d -> d.toString() }

        assertThat(info.symbol).isEqualTo("y")
        assertThat(info.creator(2.0)).isEqualTo("2.0")
    }

    @TestFactory
    fun `from value with unit`() =
        listOf(
            ValueWithUnit(2.3, "m", "y") to TestQuantity(0.002_3),
            ValueWithUnit(2.3, "k", "y") to TestQuantity(2_300),
            ValueWithUnit(2.3, "G", "y") to TestQuantity(2_300_000_000.0),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val creators = listOf(CreatorInfo("y") { value -> TestQuantity(value) })

                val result = TestQuantity.callFromValueWithUnit(input, creators)

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `from value with unit error cases`() =
        listOf(
            ValueWithUnit(2.3, "l", "y") to NoSuchPrefixException::class.java,
            ValueWithUnit(2.3, "k", "z") to NoSuchUnitException::class.java,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val creators = listOf(CreatorInfo("y") { value -> TestQuantity(value) })

                assertThatThrownBy { TestQuantity.callFromValueWithUnit(input, creators) }.isInstanceOf(expected)
            }
        }
}
