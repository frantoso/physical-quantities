package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.valueWithUnit

/**
 * A class to hold a type and unit safe charge value in Coulomb (C).
 * @param value The raw value.
 */
class Charge private constructor(
    value: Number,
) : SimpleQuantity<Charge, Charge>(value),
    Comparable<Charge> {
    /**
     * Gets the raw value in Coulomb (C).
     */
    val coulomb: Double get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Charge] instance.
     */
    override fun createFromValue(value: Number): Charge = Charge(value)

    companion object {
        /**
         * Converts a number holding a charge value to a [Charge] instance.
         * @param value The number to interpret as Coulomb.
         * @return Returns a [Charge] instance.
         */
        fun fromCoulomb(value: Number): Charge = Charge(value)
    }
}

/**
 * Converts a number holding a Coulomb value to a [Charge] instance.
 */
val Number.C: Charge get() = Charge.fromCoulomb(this)

/**
 * Creates a pair of a value and associated unit from a scaled charge quantity and 'C'.
 */
val ScaledQuantity<Charge>.C get() = valueWithUnit(this, "C")

/**
 * Creates a pair of a value and associated unit from a non-scaled charge quantity and 'C'.
 */
val Charge.C get() = valueWithUnit("C")
