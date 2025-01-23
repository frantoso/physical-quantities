package io.github.frantoso.physicalquantities.thermodynamic

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.valueWithUnit

/**
 * A class to hold a type and unit safe pressure value in Pascal (Pa).
 * @param value The raw value.
 */
class Pressure private constructor(
    value: Number,
) : SimpleQuantity<Pressure, Pressure>(value),
    Comparable<Pressure> {
    /**
     * Gets the raw value in Pascal (Pa).
     */
    val pascal: Double
        get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Pressure] instance.
     */
    override fun createFromValue(value: Number): Pressure = Pressure(value)

    companion object {
        /**
         * Converts a number holding a pressure value to a [Pressure] instance.
         * @param value The number to interpret as Pascal.
         * @return Returns a [Pressure] instance.
         */
        fun fromPascal(value: Number): Pressure = Pressure(value)
    }
}

/**
 * Converts a number holding a Pascal value to a [Pressure] instance.
 */
val Number.Pa: Pressure get() = Pressure.fromPascal(this)

/**
 * Converts a number holding a bar value to a [Pressure] instance.
 */
val Number.bar: Pressure get() = Pressure.fromPascal(toDouble() * 100_000.0)

/**
 * Creates a pair of a value and associated unit from a scaled pressure quantity and 'Pa'.
 */
val ScaledQuantity<Pressure>.Pa get() = valueWithUnit(this, "Pa")

/**
 * Creates a pair of a value and associated unit from a scaled pressure quantity and 'bar'.
 */
val ScaledQuantity<Pressure>.bar get() = valueWithUnit(this, "bar") { value -> value / 100_000.0 }

/**
 * Creates a pair of a value and associated unit from a non-scaled pressure quantity and 'Pa'.
 */
val Pressure.Pa get() = valueWithUnit("Pa")

/**
 * Creates a pair of a value and associated unit from a non-scaled pressure quantity and 'bar'.
 */
val Pressure.bar get() = valueWithUnit("bar") { value -> value / 100_000.0 }
