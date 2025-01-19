package io.github.franklisting65.physicalquantities.core

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class TestUnit(
    value: Number,
) : SimpleQuantity<TestUnit, TestUnit>(value.toDouble()) {
    override fun createFromValue(value: Number): TestUnit = TestUnit(value.toDouble())
}

class TestDiff(
    value: Number,
) : QuantityBase(value.toDouble())

class TestUnitWithDiff(
    value: Number,
) : SimpleQuantity<TestUnitWithDiff, TestDiff>(value.toDouble()) {
    override fun createFromValue(value: Number): TestUnitWithDiff = TestUnitWithDiff(value.toDouble())
}

class SimpleQuantityTest {
    @Test
    fun `test greater than`() {
        val value1 = TestUnit(41)
        val value2 = TestUnit(42.0)

        assertThat(value1 < value2).isTrue
        assertThat(value1 <= value2).isTrue
        assertThat(value1 > value2).isFalse
        assertThat(value1 >= value2).isFalse
        assertThat(value2 < value1).isFalse
        assertThat(value2 <= value1).isFalse
        assertThat(value2 > value1).isTrue
        assertThat(value2 >= value1).isTrue
    }

    @Test
    fun `test unary minus`() {
        val value1 = TestUnit(41)
        val value2 = -value1
        val value3 = -TestUnit(41)

        assertThat(value1).isInstanceOf(TestUnit::class.java)
        assertThat(value2).isInstanceOf(TestUnit::class.java)
        assertThat(value3).isInstanceOf(TestUnit::class.java)
        assertThat(value1).isNotEqualTo(value2)
        assertThat(value2).isEqualTo(value3)
        assertThat(value2.value).isEqualTo(-41.0)
    }

    @Test
    fun `test minus`() {
        val value1 = TestUnit(20)
        val value2 = TestUnit(5)

        val result = value1 - value2

        assertThat(result).isInstanceOf(TestUnit::class.java)
        assertThat(result).isEqualTo(TestUnit(15))
    }

    @Test
    fun `test plus`() {
        val value1 = TestUnit(20)
        val value2 = TestUnit(5)

        val result = value1 + value2

        assertThat(result).isInstanceOf(TestUnit::class.java)
        assertThat(result).isEqualTo(TestUnit(25))
    }

    @Test
    fun `test scaling`() {
        val value1 = TestUnit(20)
        val scale = 2

        val resultTimes = value1 * scale
        val resultDiv = value1 / scale

        assertThat(resultTimes).isInstanceOf(TestUnit::class.java)
        assertThat(resultDiv).isInstanceOf(TestUnit::class.java)
        assertThat(resultTimes).isEqualTo(TestUnit(40))
        assertThat(resultDiv).isEqualTo(TestUnit(10))
    }

    @Test
    fun `test round`() {
        val value1 = TestUnit(20.3)
        val value2 = TestUnit(20.6)

        val result1 = value1.round()
        val result2 = value2.round()

        assertThat(result1).isEqualTo(TestUnit(20))
        assertThat(result2).isEqualTo(TestUnit(21))
    }

    @Test
    fun `test minus with diff type`() {
        val value1 = TestUnitWithDiff(20)
        val value2 = TestDiff(5)

        val result = value1 - value2

        assertThat(result).isInstanceOf(TestUnitWithDiff::class.java)
        assertThat(result).isEqualTo(TestUnitWithDiff(15))
    }

    @Test
    fun `test plus diff type`() {
        val value1 = TestUnitWithDiff(20)
        val value2 = TestDiff(5)

        val result = value1 + value2

        assertThat(result).isInstanceOf(TestUnitWithDiff::class.java)
        assertThat(result).isEqualTo(TestUnitWithDiff(25))
    }
}
