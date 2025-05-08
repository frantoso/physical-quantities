package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import io.github.frantoso.physicalquantities.utils.RawType

/**
 * A class to hold a type and unit safe current value in Ampere (A).
 * @param value The raw value.
 */
class Current private constructor(
    value: Number,
) : SimpleQuantity<Current, Current>(value, BASE_SYMBOL),
    Comparable<Current> {
    /**
     * Gets the raw value in Ampere (A).
     */
    val ampere: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Current] instance.
     */
    override fun createFromValue(value: Number): Current = Current(value)

    companion object {
        const val BASE_SYMBOL = "A"

        /**
         * Converts a number holding a current value to a [Current] instance.
         * @param value The number to interpret as Ampere.
         * @return Returns a [Current] instance.
         */
        fun fromAmpere(value: Number): Current = Current(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Current = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        val creators = listOf(CreatorInfo(BASE_SYMBOL) { value -> value.A })
    }
}

/**
 * Converts a number holding an Ampere value to a [Current] instance.
 */
val Number.A: Current get() = Current.fromAmpere(this)

/**
 * Creates a pair of a value and associated unit from a scaled current quantity and 'A'.
 */
val ScaledQuantity<Current>.A get() = valueWithUnit(this, Current.BASE_SYMBOL)

/**
 * Creates a pair of a value and associated unit from a non-scaled current quantity and 'A'.
 */
val Current.A get() = valueWithUnit(Current.BASE_SYMBOL)
