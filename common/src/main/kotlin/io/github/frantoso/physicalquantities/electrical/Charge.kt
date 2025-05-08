package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import io.github.frantoso.physicalquantities.utils.RawType

/**
 * A class to hold a type and unit safe charge value in Coulomb (C).
 * @param value The raw value.
 */
class Charge private constructor(
    value: Number,
) : SimpleQuantity<Charge, Charge>(value, BASE_SYMBOL),
    Comparable<Charge> {
    /**
     * Gets the raw value in Coulomb (C).
     */
    val coulomb: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Charge] instance.
     */
    override fun createFromValue(value: Number): Charge = Charge(value)

    companion object {
        const val BASE_SYMBOL = "C"

        /**
         * Converts a number holding a charge value to a [Charge] instance.
         * @param value The number to interpret as Coulomb.
         * @return Returns a [Charge] instance.
         */
        fun fromCoulomb(value: Number): Charge = Charge(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Charge = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        val creators = listOf(CreatorInfo(BASE_SYMBOL) { value -> value.C })
    }
}

/**
 * Converts a number holding a Coulomb value to a [Charge] instance.
 */
val Number.C: Charge get() = Charge.fromCoulomb(this)

/**
 * Creates a pair of a value and associated unit from a scaled charge quantity and 'C'.
 */
val ScaledQuantity<Charge>.C get() = valueWithUnit(this, Charge.BASE_SYMBOL)

/**
 * Creates a pair of a value and associated unit from a non-scaled charge quantity and 'C'.
 */
val Charge.C get() = valueWithUnit(Charge.BASE_SYMBOL)
