package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.valueWithUnit

/**
 * A class to hold a type and unit safe voltage value in Volt (V).
 * @param value The raw value.
 */
class Voltage private constructor(
    value: Number,
) : SimpleQuantity<Voltage, Voltage>(value) {
    /**
     * Gets the raw value in Volt (V).
     */
    val volt: Double
        get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Voltage] instance.
     */
    override fun createFromValue(value: Number): Voltage = Voltage(value)

    companion object {
        /**
         * Converts a number holding a voltage value to a [Voltage] instance.
         * @param value The number to interpret as Volt.
         * @return Returns a [Voltage] instance.
         */
        fun fromVolt(value: Number): Voltage = Voltage(value)
    }
}

/**
 * Converts a number holding a Volt value to a [Voltage] instance.
 */
val Number.V: Voltage
    get() = Voltage.fromVolt(this)

/**
 * Creates a pair of a value and associated unit from a scaled voltage quantity and 'V'.
 */
val ScaledQuantity<Voltage>.V get() = valueWithUnit(this, "V")

/**
 * Creates a pair of a value and associated unit from a non-scaled voltage quantity and 'V'.
 */
val Voltage.V get() = valueWithUnit("V")
