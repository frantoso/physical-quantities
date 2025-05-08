package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import io.github.frantoso.physicalquantities.utils.RawType

/**
 * A class to hold a type and unit safe power value in Watt (W).
 * @param value The raw value.
 */
class Power private constructor(
    value: Number,
) : SimpleQuantity<Power, Power>(value, BASE_SYMBOL),
    Comparable<Power> {
    /**
     * Gets the raw value in Watt (W).
     */
    val watt: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Power] instance.
     */
    override fun createFromValue(value: Number): Power = Power(value)

    companion object {
        const val BASE_SYMBOL = "W"

        /**
         * Converts a number holding a power value to a [Power] instance.
         * @param value The number to interpret as Watt.
         * @return Returns a [Power] instance.
         */
        fun fromWatt(value: Number): Power = Power(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Power = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        val creators = listOf(CreatorInfo(BASE_SYMBOL) { value -> value.W })
    }
}

/**
 * Converts a number holding a Watt value to a [Power] instance.
 */
val Number.W: Power get() = Power.fromWatt(this)

/**
 * Creates a pair of a value and associated unit from a scaled power quantity and 'W'.
 */
val ScaledQuantity<Power>.W get() = valueWithUnit(this, Power.BASE_SYMBOL)

/**
 * Creates a pair of a value and associated unit from a non-scaled power quantity and 'W'.
 */
val Power.W get() = valueWithUnit(Power.BASE_SYMBOL)
