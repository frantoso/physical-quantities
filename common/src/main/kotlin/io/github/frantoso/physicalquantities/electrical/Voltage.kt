package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import io.github.frantoso.physicalquantities.utils.RawType

/**
 * A class to hold a type and unit safe voltage value in Volt (V).
 * @param value The raw value.
 */
class Voltage private constructor(
    value: Number,
) : SimpleQuantity<Voltage, Voltage>(value, BASE_SYMBOL) {
    /**
     * Gets the raw value in Volt (V).
     */
    val volt: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Voltage] instance.
     */
    override fun createFromValue(value: Number): Voltage = Voltage(value)

    companion object {
        const val BASE_SYMBOL = "V"

        /**
         * Converts a number holding a voltage value to a [Voltage] instance.
         * @param value The number to interpret as Volt.
         * @return Returns a [Voltage] instance.
         */
        fun fromVolt(value: Number): Voltage = Voltage(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Voltage = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        private val creators = listOf(CreatorInfo(BASE_SYMBOL) { value -> value.V })
    }
}

/**
 * Converts a number holding a Volt value to a [Voltage] instance.
 */
val Number.V: Voltage get() = Voltage.fromVolt(this)

/**
 * Creates a pair of a value and associated unit from a scaled voltage quantity and 'V'.
 */
val ScaledQuantity<Voltage>.V get() = valueWithUnit(this, Voltage.BASE_SYMBOL)

/**
 * Creates a pair of a value and associated unit from a non-scaled voltage quantity and 'V'.
 */
val Voltage.V get() = valueWithUnit(Voltage.BASE_SYMBOL)
