package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit

/**
 * A class to hold a type and unit safe energy value in Joule (J).
 * @param value The raw value.
 */
class Energy private constructor(
    value: Number,
) : SimpleQuantity<Energy, Energy>(value, BASE_SYMBOL),
    Comparable<Energy> {
    /**
     * Gets the raw value in Ohm (O).
     */
    val joule: Double get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Energy] instance.
     */
    override fun createFromValue(value: Number): Energy = Energy(value)

    companion object {
        const val BASE_SYMBOL = "J"

        /**
         * Converts a number holding an energy value to an [Energy] instance.
         * @param value The number to interpret as Joule.
         * @return Returns a [Energy] instance.
         */
        fun fromJoule(value: Number): Energy = Energy(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Energy = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        private val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.J },
                CreatorInfo("Ws") { value -> value.Ws },
                CreatorInfo("Wh") { value -> value.Wh },
            )
    }
}

/**
 * Converts a number holding a Joule value to a [Energy] instance.
 */
val Number.J: Energy get() = Energy.fromJoule(this)
val Number.Ws: Energy get() = Energy.fromJoule(this)
val Number.Wh: Energy get() = Energy.fromJoule(this.toDouble() * 3_600.0)

/**
 * Creates a pair of a value and associated unit from a scaled energy quantity and the unit.
 */
val ScaledQuantity<Energy>.J get() = valueWithUnit(this, Energy.BASE_SYMBOL)
val ScaledQuantity<Energy>.Ws get() = valueWithUnit(this, "Ws")
val ScaledQuantity<Energy>.Wh get() = valueWithUnit(this, "Wh") { value -> value / 3_600.0 }

/**
 * Creates a pair of a value and associated unit from a non-scaled energy quantity and the unit.
 */
val Energy.J get() = valueWithUnit(Energy.BASE_SYMBOL)
val Energy.Ws get() = valueWithUnit("Ws")
val Energy.Wh get() = valueWithUnit("Wh") { value -> value / 3_600.0 }
