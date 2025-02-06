package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import java.math.BigDecimal

/**
 * A class to hold a type and unit safe resistance value in Ohm (O).
 * @param value The raw value.
 */
class Resistance private constructor(
    value: Number,
) : SimpleQuantity<Resistance, Resistance>(value, BASE_SYMBOL),
    Comparable<Resistance> {
    /**
     * Gets the raw value in Ohm (O).
     */
    val ohm: BigDecimal get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Resistance] instance.
     */
    override fun createFromValue(value: Number): Resistance = Resistance(value)

    companion object {
        const val BASE_SYMBOL = "Ω"

        /**
         * Converts a number holding a resistance value to a [Resistance] instance.
         * @param value The number to interpret as Ohm.
         * @return Returns a [Resistance] instance.
         */
        fun fromOhm(value: Number): Resistance = Resistance(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Resistance = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        private val creators = listOf(CreatorInfo(BASE_SYMBOL) { value -> value.O })
    }
}

/**
 * Converts a number holding an Ohm value to a [Resistance] instance.
 */
val Number.O: Resistance get() = Resistance.fromOhm(this)

/**
 * Creates a pair of a value and associated unit from a scaled resistance quantity and 'Ω'.
 */
val ScaledQuantity<Resistance>.O get() = valueWithUnit(this, Resistance.BASE_SYMBOL)

/**
 * Creates a pair of a value and associated unit from a non-scaled resistance quantity and 'Ω'.
 */
val Resistance.O get() = valueWithUnit(Resistance.BASE_SYMBOL)
