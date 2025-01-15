package de.franklisting.physicalquantities.electrical

import de.franklisting.physicalquantities.core.SimpleUnit

/**
 * A class to hold a type and unit safe current value in Ampere (A).
 * @param value The raw value.
 */
class Current private constructor(value: Number) : SimpleUnit<Current, Current>(value), Comparable<Current> {
    /**
     * Gets the raw value in Ampere (A).
     */
    val ampere: Double
        get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created Current instance.
     */
    override fun createFromValue(value: Number): Current = Current(value)

    companion object {
        /**
         * Converts a number holding a current value to a Current instance.
         * @param value The number to interpret as Ampere.
         * @return Returns a Current instance.
         */
        fun fromAmpere(value: Number): Current = Current(value)
    }
}

/**
 * Converts a number holding an Ampere value to a Current instance.
 */
val Number.A: Current
    get() = Current.fromAmpere(this)
