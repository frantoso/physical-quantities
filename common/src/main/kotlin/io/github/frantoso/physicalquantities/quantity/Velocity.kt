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
 * A class to hold a type and unit safe velocity value in meters per second (m/s).
 * @param value The raw value.
 */
class Velocity private constructor(
    value: Number,
) : SimpleQuantity<Velocity, Velocity>(value, BASE_SYMBOL),
    Comparable<Velocity> {
    /**
     * Gets the raw value in meters per second (m/s).
     */
    val metersPerSecond: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Velocity] instance.
     */
    override fun createFromValue(value: Number): Velocity = Velocity(value)

    companion object {
        const val BASE_SYMBOL = "m/s"

        /**
         * Converts a number holding a velocity value to a [Velocity] instance.
         * @param value The number to interpret as meters per second.
         * @return Returns a [Velocity] instance.
         */
        fun fromMetersPerSecond(value: Number): Velocity = Velocity(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Velocity = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.mPerS },
                CreatorInfo("km/h") { value -> value.kmPerH },
            )
    }
}

/**
 * Converts a number holding a meters per second value to a [Velocity] instance.
 */
val Number.mPerS: Velocity get() = Velocity.fromMetersPerSecond(this)

/**
 * Converts a number holding a kilometers per hour value to a [Velocity] instance.
 */
val Number.kmPerH: Velocity get() = Velocity.fromMetersPerSecond(toRawType().divideBy(3.6.toRawType()))

/**
 * Creates a pair of a value and associated unit from a scaled velocity quantity and the unit.
 */
val ScaledQuantity<Velocity>.mPerS get() = valueWithUnit(this, Velocity.BASE_SYMBOL)
val ScaledQuantity<Velocity>.kmPerH get() = valueWithUnit(this, "km/h") { value -> value.times(3.6.toRawType()) }

/**
 * Creates a pair of a value and associated unit from a non-scaled velocity quantity and the unit.
 */
val Velocity.mPerS get() = valueWithUnit(Velocity.BASE_SYMBOL)
val Velocity.kmPerH get() = valueWithUnit("km/h") { value -> value.times(3.6.toRawType()) }
