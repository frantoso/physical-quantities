package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions
import kotlin.test.Test

class PowerTest {
    @Test
    fun `test initialisation`() {
        val power = Power.fromWatt(24)

        Assertions.assertThat(power.watt).isEqualTo(24.toRawType())
    }

    @Test
    fun `test initialisation from literal`() {
        val power = 24.W

        Assertions.assertThat(power.watt).isEqualTo(24.toRawType())
    }

    @Test
    fun `test createFromValue`() {
        val power = -(24.W) // base class calls createFromValue()

        Assertions.assertThat(power.watt).isEqualTo((-24).toRawType())
    }

    @Test
    fun `to value with unit`() {
        val power = 32.W

        val result = power.W

        Assertions.assertThat(result).isEqualTo(ValueWithUnit(32.0, "", "W"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val power = 42.W

        val result = power._m.W

        Assertions.assertThat(result).isEqualTo(ValueWithUnit(42000.0, "m", "W"))
    }

    @Test
    fun `from value with unit`() {
        val input = ValueWithUnit(2.3, "m", "W")
        val result = Power.fromValueWithUnit(input)

        Assertions.assertThat(result).isEqualTo(2.3._m.W)
    }
}
