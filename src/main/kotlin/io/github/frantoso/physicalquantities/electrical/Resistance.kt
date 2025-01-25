package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.valueWithUnit

/**
 * A class to hold a type and unit safe resistance value in Ohm (O).
 * @param value The raw value.
 */
class Resistance private constructor(
    value: Number,
) : SimpleQuantity<Resistance, Resistance>(value),
    Comparable<Resistance> {
    /**
     * Gets the raw value in Ohm (O).
     */
    val ohm: Double get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Resistance] instance.
     */
    override fun createFromValue(value: Number): Resistance = Resistance(value)

    companion object {
        /**
         * Converts a number holding a resistance value to a [Resistance] instance.
         * @param value The number to interpret as Ohm.
         * @return Returns a [Resistance] instance.
         */
        fun fromOhm(value: Number): Resistance = Resistance(value)
    }
}

/**
 * Converts a number holding an Ohm value to a [Resistance] instance.
 */
val Number.O: Resistance get() = Resistance.fromOhm(this)

/**
 * Creates a pair of a value and associated unit from a scaled resistance quantity and 'Ω'.
 */
val ScaledQuantity<Resistance>.O get() = valueWithUnit(this, "Ω")

/**
 * Creates a pair of a value and associated unit from a non-scaled resistance quantity and 'Ω'.
 */
val Resistance.O get() = valueWithUnit("Ω")
