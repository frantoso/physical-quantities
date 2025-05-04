package io.github.frantoso.physicalquantities.quantity

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
 * A class to hold a type and unit safe length value in Meter (m).
 * @param value The raw value.
 */
class Length private constructor(
    value: Number,
) : SimpleQuantity<Length, Length>(value, BASE_SYMBOL),
    Comparable<Length> {
    /**
     * Gets the raw value in Meter (m).
     */
    val meter: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Length] instance.
     */
    override fun createFromValue(value: Number): Length = Length(value)

    companion object {
        const val BASE_SYMBOL = "m"

        /**
         * Converts a number holding a length value to a [Length] instance.
         * @param value The number to interpret as Meter.
         * @return Returns a [Length] instance.
         */
        fun fromMeter(value: Number): Length = Length(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Length = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        private val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.m },
                CreatorInfo("in") { value -> value.inch },
                CreatorInfo("ft") { value -> value.ft },
                CreatorInfo("mile") { value -> value.mile },
            )
    }
}

/**
 * Converts a number holding a Meter value to a [Length] instance.
 */
val Number.m: Length get() = Length.fromMeter(this)

/**
 * Converts a number holding an inch value to a [Length] instance.
 */
val Number.inch: Length get() = Length.fromMeter(this.toRawType().times(0.0254.toRawType()))

/**
 * Converts a number holding a foot value to a [Length] instance.
 */
val Number.ft: Length get() = Length.fromMeter(this.toRawType().times(0.3048.toRawType()))

/**
 * Converts a number holding a mile value to a [Length] instance.
 */
val Number.mile: Length get() = Length.fromMeter(this.toRawType().times(1609.344.toRawType()))

/**
 * Creates a pair of a value and associated unit from a scaled length quantity and 'm'.
 */
val ScaledQuantity<Length>.m get() = valueWithUnit(this, Length.BASE_SYMBOL)
val ScaledQuantity<Length>.inch get() = valueWithUnit(this, "in") { value -> value.divideBy(0.0254.toRawType()) }
val ScaledQuantity<Length>.ft get() = valueWithUnit(this, "ft") { value -> value.divideBy(0.3048.toRawType()) }
val ScaledQuantity<Length>.mile get() = valueWithUnit(this, "mile") { value -> value.divideBy(1609.344.toRawType()) }

/**
 * Creates a pair of a value and associated unit from a non-scaled length quantity and 'm'.
 */
val Length.m get() = valueWithUnit(Length.BASE_SYMBOL)
val Length.inch get() = valueWithUnit("in") { value -> value.divideBy(0.0254.toRawType()) }
val Length.ft get() = valueWithUnit("ft") { value -> value.divideBy(0.3048.toRawType()) }
val Length.mile get() = valueWithUnit("mile") { value -> value.divideBy(1609.344.toRawType()) }
