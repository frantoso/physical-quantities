package io.github.frantoso.physicalquantities.electrical

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class PowerTest {
    @Test
    fun `test initialisation`() {
        val power = Power.fromWatt(24)

        assertThat(power.watt).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal`() {
        val power = 24.W

        assertThat(power.watt).isEqualTo(24.0)
    }

    @Test
    fun `test createFromValue`() {
        val power = -(24.W) // base class calls createFromValue()

        assertThat(power.watt).isEqualTo(-24.0)
    }
}
