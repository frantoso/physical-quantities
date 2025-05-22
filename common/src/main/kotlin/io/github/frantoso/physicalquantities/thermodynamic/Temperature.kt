@file:Suppress("NonAsciiCharacters", "ObjectPropertyName")

package io.github.frantoso.physicalquantities.thermodynamic

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import io.github.frantoso.physicalquantities.utils.RawType
import io.github.frantoso.physicalquantities.utils.toRawType

/**
 * A class to hold a type and unit safe temperature value in Kelvin (K).
 * @param value The raw value.
 */
class Temperature private constructor(
    value: Number,
) : SimpleQuantity<Temperature, TemperatureDifference>(value, BASE_SYMBOL),
    Comparable<Temperature> {
    /**
     * Gets the raw value in Kelvin (K).
     */
    val kelvin: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Temperature] instance.
     */
    override fun createFromValue(value: Number): Temperature = Temperature(value)

    companion object {
        const val BASE_SYMBOL = "K"

        /**
         * Converts a number holding a temperature value to a [Temperature] instance.
         * @param value The number to interpret as Kelvin.
         * @return Returns a [Temperature] instance.
         */
        fun fromKelvin(value: Number): Temperature = Temperature(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Temperature = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.K },
                CreatorInfo("Celsius") { value -> value.Celsius },
                CreatorInfo("°C") { value -> value.Celsius },
            )
    }
}

/**
 * Converts a number holding a Kelvin value to a [Temperature] instance.
 */
val Number.K: Temperature get() = Temperature.fromKelvin(this)

/**
 * Converts a number holding a °Celsius value to a [Temperature] instance.
 */
val Number.Celsius: Temperature get() = Temperature.fromKelvin(toRawType().plus(273.15.toRawType()))

/**
 * Converts a number holding a °Celsius value to a [Temperature] instance.
 */
val Number.`°C`: Temperature get() = Celsius

/**
 * Creates a pair of a value and associated unit from a scaled temperature quantity and 'K'.
 */
val ScaledQuantity<Temperature>.K get() = valueWithUnit(this, Temperature.BASE_SYMBOL)

/**
 * Creates a pair of a value and associated unit from a scaled temperature quantity and '°C'.
 */
val ScaledQuantity<Temperature>.Celsius
    get() =
        valueWithUnit(
            this,
            "°C",
        ) { value -> value.minus(273.15.toRawType()) }

/**
 * Creates a pair of a value and associated unit from a scaled temperature quantity and '°C'.
 */
val ScaledQuantity<Temperature>.`°C` get() = Celsius

/**
 * Creates a pair of a value and associated unit from a non-scaled temperature quantity and 'K'.
 */
val Temperature.K get() = valueWithUnit(Temperature.BASE_SYMBOL)

/**
 * Creates a pair of a value and associated unit from a non-scaled temperature quantity and '°C'.
 */
val Temperature.Celsius get() = valueWithUnit("°C") { value -> value.minus(273.15.toRawType()) }

/**
 * Creates a pair of a value and associated unit from a non-scaled temperature quantity and '°C'.
 */
val Temperature.`°C` get() = Celsius
