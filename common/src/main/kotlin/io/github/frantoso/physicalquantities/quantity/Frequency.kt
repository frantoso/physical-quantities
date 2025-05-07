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
 * A class to hold a type and unit safe frequency value in Hertz (Hz)
 * @param value The raw value.
 */
class Frequency private constructor(
    value: Number,
) : SimpleQuantity<Frequency, Frequency>(value, BASE_SYMBOL),
    Comparable<Frequency> {
    /**
     * Gets the raw value in Hertz (Hz).
     */
    val hertz: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Frequency] instance.
     */
    override fun createFromValue(value: Number): Frequency = Frequency(value)

    companion object {
        const val BASE_SYMBOL = "Hz"

        /**
         * Converts a number holding a frequency value to a [Frequency] instance.
         * @param value The number to interpret as Hertz.
         * @return Returns a [Frequency] instance.
         */
        fun fromHertz(value: Number): Frequency = Frequency(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Frequency = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        private val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.Hz },
                CreatorInfo("rpm") { value -> value.rpm },
            )
    }
}

/**
 * Converts a number holding a Hertz value to a [Frequency] instance.
 */
val Number.Hz: Frequency get() = Frequency.fromHertz(this)

/**
 * Converts a number holding a rpm value to a [Frequency] instance.
 */
val Number.rpm: Frequency get() = Frequency.fromHertz(toRawType().divideBy(60.toRawType()))

/**
 * Creates a pair of a value and associated unit from a scaled frequency quantity and 'Hz'.
 */
val ScaledQuantity<Frequency>.Hz get() = valueWithUnit(this, Frequency.BASE_SYMBOL)
val ScaledQuantity<Frequency>.rpm get() = valueWithUnit(this, "rpm") { value -> value.times(60.toRawType()) }

/**
 * Creates a pair of a value and associated unit from a non-scaled frequency quantity and 'Hz'.
 */
val Frequency.Hz get() = valueWithUnit(Frequency.BASE_SYMBOL)
val Frequency.rpm get() = valueWithUnit("rpm") { value -> value.times(60.toRawType()) }
