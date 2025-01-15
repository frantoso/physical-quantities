package de.franklisting.physicalquantities.electrical

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
}
