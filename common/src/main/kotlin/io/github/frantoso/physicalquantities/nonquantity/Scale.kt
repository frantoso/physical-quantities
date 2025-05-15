package io.github.frantoso.physicalquantities.nonquantity

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import io.github.frantoso.physicalquantities.utils.RawType
import io.github.frantoso.physicalquantities.utils.divideBy
import io.github.frantoso.physicalquantities.utils.toRawType

/**
 * A class to hold a type and unit safe scale value as Number.
 * @param value The raw value.
 */
class Scale private constructor(
    value: Number,
) : SimpleQuantity<Scale, Scale>(value, BASE_SYMBOL),
    Comparable<Scale> {
    /**
     * Gets the raw value.
     */
    val scale: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Scale] instance.
     */
    override fun createFromValue(value: Number): Scale = Scale(value)

    companion object {
        const val BASE_SYMBOL = "sc"

        /**
         * Converts a number holding a number to a [Scale] instance.
         * @param value The number to interpret.
         * @return Returns a [Scale] instance.
         */
        fun fromNumber(value: Number): Scale = Scale(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Scale = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.sc },
                CreatorInfo("%") { value -> value.percent },
                CreatorInfo("percent") { value -> value.percent },
                CreatorInfo("‰") { value -> value.permille },
                CreatorInfo("permille") { value -> value.permille },
            )
    }
}

/**
 * Converts a number to a [Scale] instance.
 */
val Number.sc: Scale get() = Scale.fromNumber(this)

/**
 * Converts a percentage to a [Scale] instance.
 */
val Number.percent: Scale get() = Scale.fromNumber(this.toRawType().divideBy(100.toRawType()))

/**
 * Converts a per mille to a [Scale] instance.
 */
val Number.permille: Scale get() = Scale.fromNumber(this.toRawType().divideBy(1000.toRawType()))

/**
 * Creates a pair of a value and associated unit from a scaled scale quantity and the unit.
 */
val ScaledQuantity<Scale>.sc get() = valueWithUnit(this, Scale.BASE_SYMBOL)
val ScaledQuantity<Scale>.percent get() = valueWithUnit(this, "%") { value -> value.times(100.toRawType()) }
val ScaledQuantity<Scale>.permille get() = valueWithUnit(this, "‰") { value -> value.times(1000.toRawType()) }

/**
 * Creates a pair of a value and associated unit from a non-scaled scale quantity and the unit.
 */
val Scale.sc get() = valueWithUnit(Scale.BASE_SYMBOL)
val Scale.percent get() = valueWithUnit("%") { value -> value.times(100.toRawType()) }
val Scale.permille get() = valueWithUnit("‰") { value -> value.times(1000.toRawType()) }
