package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.valueWithUnit

/**
 * A class to hold a type and unit safe frequency value in Hertz(Hz)
 * @param value The raw value.
 */
class Frequency private constructor(
    value: Number,
) : SimpleQuantity<Frequency, Frequency>(value),
    Comparable<Frequency> {
    /**
     * Gets the raw value in Hertz (Hz).
     */
    val hertz: Double get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Frequency] instance.
     */
    override fun createFromValue(value: Number): Frequency = Frequency(value)

    companion object {
        /**
         * Converts a number holding a frequency value to a [Frequency] instance.
         * @param value The number to interpret as Hertz.
         * @return Returns a [Frequency] instance.
         */
        fun fromHertz(value: Number): Frequency = Frequency(value)
    }
}

/**
 * Converts a number holding a Hertz value to a [Frequency] instance.
 */
val Number.Hz: Frequency get() = Frequency.fromHertz(this)

/**
 * Creates a pair of a value and associated unit from a scaled frequency quantity and 'Hz'.
 */
val ScaledQuantity<Frequency>.Hz get() = valueWithUnit(this, "Hz")

/**
 * Creates a pair of a value and associated unit from a non-scaled frequency quantity and 'Hz'.
 */
val Frequency.Hz get() = valueWithUnit("Hz")
