package de.franklisting.physicalquantities.electrical

import de.franklisting.physicalquantities.core.SimpleUnit

/**
 * A class to hold a type and unit safe power value in Watt (W).
 * @param value The raw value.
 */
class Power private constructor(
    value: Number,
) : SimpleUnit<Power, Power>(value),
    Comparable<Power> {
    /**
     * Gets the raw value in Watt (w).
     */
    val watt: Double
        get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created Power instance.
     */
    override fun createFromValue(value: Number): Power = Power(value)

    companion object {
        /**
         * Converts a number holding a power value to a Power instance.
         * @param value The number to interpret as Watt.
         * @return Returns a Power instance.
         */
        fun fromWatt(value: Number): Power = Power(value)
    }
}

/**
 * Converts a number holding a Watt value to a Power instance.
 */
val Number.W: Power
    get() = Power.fromWatt(this)
