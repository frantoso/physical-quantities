package de.franklisting.physicalquantities.electrical

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class VoltageTest {
    @Test
    fun `test initialisation`() {
        val voltage = Voltage.fromVolt(24)

        assertThat(voltage.volt).isEqualTo(24.0)
    }

    @Test
    fun `test initialisation from literal`() {
        val voltage = 24.V

        assertThat(voltage.volt).isEqualTo(24.0)
    }

    @Test
    fun `test createFromValue`() {
        val voltage = -(24.V) // base class calls createFromValue()

        assertThat(voltage.volt).isEqualTo(-24.0)
    }
}
