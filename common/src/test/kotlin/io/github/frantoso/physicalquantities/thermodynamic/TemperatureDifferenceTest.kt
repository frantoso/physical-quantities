package io.github.frantoso.physicalquantities.thermodynamic

import io.github.frantoso.physicalquantities.testUtils.STANDARD_OFFSET
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

@Suppress("NonAsciiCharacters")
class TemperatureDifferenceTest {
    @Test
    fun `test initialisation`() {
        val difference = TemperatureDifference.fromDegrees(10)

        assertThat(difference.degrees).isEqualTo(10.toRawType())
    }

    @Test
    fun `test initialisation from literal (°)`() {
        val difference = 17.23.`°`

        assertThat(difference.degrees).isEqualTo(17.23.toRawType())
    }

    @Test
    fun `test initialisation from literal (Degrees)`() {
        val difference = 27.23.Degrees

        assertThat(difference.degrees).isEqualTo(27.23.toRawType())
    }

    @Test
    fun `can add degrees to temperature`() {
        val temperature = 20.`°C`

        val result = temperature + 5.`°`

        assertThat(result.kelvin).isCloseTo(298.15.toRawType(), STANDARD_OFFSET)
        assertThat(result.Celsius.value).isCloseTo(25.toRawType(), STANDARD_OFFSET)
    }

    @Test
    fun `can subtract degrees from temperature`() {
        val temperature = 20.`°C`

        val result = temperature - 5.`°`

        assertThat(result.kelvin).isCloseTo(288.15.toRawType(), STANDARD_OFFSET)
        assertThat(result.Celsius.value).isCloseTo(15.toRawType(), STANDARD_OFFSET)
    }
}
