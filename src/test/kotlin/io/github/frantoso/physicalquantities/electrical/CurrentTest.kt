package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core._m
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class CurrentTest {
    @Test
    fun `test initialisation`() {
        val current = Current.fromAmpere(24)

        assertThat(current.ampere).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal`() {
        val current = 24.A

        assertThat(current.ampere).isEqualTo(24.0)
    }

    @Test
    fun `test createFromValue`() {
        val current = -(24.A) // base class calls createFromValue()

        assertThat(current.ampere).isEqualTo(-24.0)
    }

    @Test
    fun `to value with unit`() {
        val current = 32.A

        val result = current.A

        assertThat(result).isEqualTo(ValueWithUnit(32.0, "", "A"))
    }

    @Test
    fun `to value with unit from ScaledQuantity`() {
        val current = 42.A

        val result = current._m.A

        assertThat(result).isEqualTo(ValueWithUnit(42000.0, "m", "A"))
    }
}
