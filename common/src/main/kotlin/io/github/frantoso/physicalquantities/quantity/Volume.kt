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
 * A class to hold a type and unit safe volume value in Cubic Meter (m³).
 * @param value The raw value.
 */
class Volume private constructor(
    value: Number,
) : SimpleQuantity<Volume, Volume>(value, BASE_SYMBOL),
    Comparable<Volume> {
    /**
     * Gets the raw value in Cubic Meters (m³).
     */
    val cubicMeter: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Volume] instance.
     */
    override fun createFromValue(value: Number): Volume = Volume(value)

    companion object {
        const val BASE_SYMBOL = "m³"

        /**
         * Converts a number holding a volume value to a [Volume] instance.
         * @param value The number to interpret as Cubic Meter.
         * @return Returns a [Volume] instance.
         */
        fun fromCubicMeter(value: Number): Volume = Volume(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Volume = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        private val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.m3 },
                CreatorInfo("l") { value -> value.l },
            )
    }
}

/**
 * Converts a number holding a cubic meter value to a [Volume] instance.
 */
val Number.m3: Volume get() = Volume.fromCubicMeter(this)

/**
 * Converts a number holding a Liter value to a [Volume] instance.
 */
val Number.l: Volume get() = Volume.fromCubicMeter(this.toRawType().divideBy(1000.toRawType()))

/**
 * Creates a pair of a value and associated unit from a scaled volume quantity and 'l'.
 */
val ScaledQuantity<Volume>.m3 get() = valueWithUnit(this, Volume.BASE_SYMBOL)
val ScaledQuantity<Volume>.l get() = valueWithUnit(this, "l") { value -> value.times(1000.toRawType()) }

/**
 * Creates a pair of a value and associated unit from a non-scaled volume quantity and 'l'.
 */
val Volume.m3 get() = valueWithUnit(Volume.BASE_SYMBOL)
val Volume.l get() = valueWithUnit("l") { value -> value.times(1000.toRawType()) }
