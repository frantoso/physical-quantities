package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import io.github.frantoso.physicalquantities.utils.RawType

/**
 * A class to hold a type and unit safe amount of substance value in Mole (mol).
 * @param value The raw value.
 */
class AmountOfSubstance private constructor(
    value: Number,
) : SimpleQuantity<AmountOfSubstance, AmountOfSubstance>(value, BASE_SYMBOL),
    Comparable<AmountOfSubstance> {
    /**
     * Gets the raw value in Mole (mol).
     */
    val mole: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [AmountOfSubstance] instance.
     */
    override fun createFromValue(value: Number): AmountOfSubstance = AmountOfSubstance(value)

    companion object {
        const val BASE_SYMBOL = "mol"

        /**
         * Converts a number holding a amount of substance value to a [AmountOfSubstance] instance.
         * @param value The number to interpret as mol.
         * @return Returns a [AmountOfSubstance] instance.
         */
        fun fromMole(value: Number): AmountOfSubstance = AmountOfSubstance(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): AmountOfSubstance = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        private val creators = listOf(CreatorInfo(BASE_SYMBOL) { value -> value.mol })
    }
}

/**
 * Converts a number holding a Mole value to a [AmountOfSubstance] instance.
 */
val Number.mol: AmountOfSubstance get() = AmountOfSubstance.fromMole(this)

/**
 * Creates a pair of a value and associated unit from a scaled amount of substance quantity and 'mol'.
 */
val ScaledQuantity<AmountOfSubstance>.mol get() = valueWithUnit(this, AmountOfSubstance.BASE_SYMBOL)

/**
 * Creates a pair of a value and associated unit from a non-scaled amount of substance quantity and 'mol'.
 */
val AmountOfSubstance.mol get() = valueWithUnit(AmountOfSubstance.BASE_SYMBOL)
