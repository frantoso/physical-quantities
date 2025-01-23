@file:Suppress("NonAsciiCharacters", "ObjectPropertyName")

package io.github.frantoso.physicalquantities.thermodynamic

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.valueWithUnit

/**
 * A class to hold a type and unit safe temperature value in Kelvin (K).
 * @param value The raw value.
 */
class Temperature private constructor(
    value: Number,
) : SimpleQuantity<Temperature, TemperatureDifference>(value),
    Comparable<Temperature> {
    /**
     * Gets the raw value in Kelvin (K).
     */
    val kelvin: Double
        get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Temperature] instance.
     */
    override fun createFromValue(value: Number): Temperature = Temperature(value)

    companion object {
        /**
         * Converts a number holding a temperature value to a [Temperature] instance.
         * @param value The number to interpret as Kelvin.
         * @return Returns a [Temperature] instance.
         */
        fun fromKelvin(value: Number): Temperature = Temperature(value)
    }
}

/**
 * Converts a number holding a Kelvin value to a [Temperature] instance.
 */
val Number.K: Temperature get() = Temperature.fromKelvin(this)

/**
 * Converts a number holding a °Celsius value to a [Temperature] instance.
 */
val Number.Celsius: Temperature get() = Temperature.fromKelvin(toDouble() + 273.15)

/**
 * Converts a number holding a °Celsius value to a [Temperature] instance.
 */
val Number.`°C`: Temperature get() = Celsius

/**
 * Creates a pair of a value and associated unit from a scaled temperature quantity and 'K'.
 */
val ScaledQuantity<Temperature>.K get() = valueWithUnit(this, "K")

/**
 * Creates a pair of a value and associated unit from a scaled temperature quantity and '°C'.
 */
val ScaledQuantity<Temperature>.Celsius get() = valueWithUnit(this, "°C") { value -> value - 273.15 }

/**
 * Creates a pair of a value and associated unit from a scaled temperature quantity and '°C'.
 */
val ScaledQuantity<Temperature>.`°C` get() = Celsius

/**
 * Creates a pair of a value and associated unit from a non-scaled temperature quantity and 'K'.
 */
val Temperature.K get() = valueWithUnit("K")

/**
 * Creates a pair of a value and associated unit from a non-scaled temperature quantity and '°C'.
 */
val Temperature.Celsius get() = valueWithUnit("°C") { value -> value - 273.15 }

/**
 * Creates a pair of a value and associated unit from a non-scaled temperature quantity and '°C'.
 */
val Temperature.`°C` get() = Celsius
