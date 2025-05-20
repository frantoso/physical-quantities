package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import io.github.frantoso.physicalquantities.utils.RawType

/**
 * A class to hold a type and unit safe acidity value in pH.
 * @param value The raw value.
 */
class Acidity private constructor(
    value: Number,
) : SimpleQuantity<Acidity, Acidity>(value, BASE_SYMBOL),
    Comparable<Acidity> {
    /**
     * Gets the raw value in pH.
     */
    val phValue: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Acidity] instance.
     */
    override fun createFromValue(value: Number): Acidity = Acidity(value)

    companion object {
        const val BASE_SYMBOL = "pH"

        /**
         * Converts a number holding an acidity value to a [Acidity] instance.
         * @param value The number to interpret as pH.
         * @return Returns a [Acidity] instance.
         */
        fun fromPh(value: Number): Acidity = Acidity(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Acidity = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        val creators = listOf(CreatorInfo(BASE_SYMBOL) { value -> value.pH })
    }
}

/**
 * Converts a number holding an pH value to a [Acidity] instance.
 */
val Number.pH: Acidity get() = Acidity.fromPh(this)

/**
 * Creates a pair of a value and associated unit from a scaled acidity quantity and 'pH'.
 */
val ScaledQuantity<Acidity>.pH get() = valueWithUnit(this, Acidity.BASE_SYMBOL)

/**
 * Creates a pair of a value and associated unit from a non-scaled acidity quantity and 'pH'.
 */
val Acidity.pH get() = valueWithUnit(Acidity.BASE_SYMBOL)
