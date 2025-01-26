package io.github.frantoso.physicalquantities.thermodynamic

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import kotlin.test.Test

@Suppress("NonAsciiCharacters")
class TemperatureDifferenceTest {
    @Test
    fun `test initialisation`() {
        val difference = TemperatureDifference.fromDegrees(10)

        assertThat(difference.degrees).isEqualTo(10.0)
    }

    @Test
    fun `test initialisation from literal (°)`() {
        val difference = 17.23.`°`

        assertThat(difference.degrees).isEqualTo(17.23)
    }

    @Test
    fun `test initialisation from literal (Degrees)`() {
        val difference = 27.23.Degrees

        assertThat(difference.degrees).isEqualTo(27.23)
    }

    @Test
    fun `can add degrees to temperature`() {
        val temperature = 20.`°C`

        val result = temperature + 5.`°`

        assertThat(result.kelvin).isCloseTo(298.15, Offset.offset(0.00000001))
        assertThat(result.Celsius.value).isCloseTo(25.0, Offset.offset(0.00000001))
    }

    @Test
    fun `can subtract degrees from temperature`() {
        val temperature = 20.`°C`

        val result = temperature - 5.`°`

        assertThat(result.kelvin).isCloseTo(288.15, Offset.offset(0.00000001))
        assertThat(result.Celsius.value).isCloseTo(15.0, Offset.offset(0.00000001))
    }
}
