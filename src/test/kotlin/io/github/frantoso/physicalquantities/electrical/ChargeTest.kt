package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._m
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ChargeTest {
    @Test
    fun `test initialisation`() {
        val charge = Charge.fromCoulomb(24)

        assertThat(charge.coulomb).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal`() {
        val charge = 24.C

        assertThat(charge.coulomb).isEqualTo(24.0)
    }

    @Test
    fun `test createFromValue`() {
        val charge = -(24.C) // base class calls createFromValue()

        assertThat(charge.coulomb).isEqualTo(-24.0)
    }

    @Test
    fun `to value with unit`() {
        val charge = 32.C

        val result = charge.C

        assertThat(result).isEqualTo(ValueWithUnit(32.0, "", "C"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val charge = 42.C

        val result = charge._m.C

        assertThat(result).isEqualTo(ValueWithUnit(42000.0, "m", "C"))
    }
}
