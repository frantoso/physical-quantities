package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.utils.toBigDecimal
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ChargeTest {
    @Test
    fun `test initialisation`() {
        val charge = Charge.fromCoulomb(24)

        assertThat(charge.coulomb).isEqualTo(24.toBigDecimal())
    }

    @Test
    fun `test initialisation from literal`() {
        val charge = 24.C

        assertThat(charge.coulomb).isEqualTo(24.toBigDecimal())
    }

    @Test
    fun `test createFromValue`() {
        val charge = -(24.C) // base class calls createFromValue()

        assertThat(charge.coulomb).isEqualTo((-24).toBigDecimal())
    }

    @Test
    fun `to value with unit`() {
        val charge = 32.C

        val result = charge.C

        assertThat(result).isEqualTo(ValueWithUnit(32, "", "C"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val charge = 42.C

        val result = charge._m.C

        assertThat(result).isEqualTo(ValueWithUnit(42000, "m", "C"))
    }

    @Test
    fun `from value with unit`() {
        val input = ValueWithUnit(2.3, "m", "C")
        val result = Charge.fromValueWithUnit(input)

        assertThat(result).isEqualTo(2.3._m.C)
    }
}
