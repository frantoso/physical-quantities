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
 * A class to hold a type and unit safe flow rate value in m³/s.
 * @param value The raw value.
 */
class FlowRate private constructor(
    value: Number,
) : SimpleQuantity<FlowRate, FlowRate>(value, BASE_SYMBOL),
    Comparable<FlowRate> {
    /**
     * Gets the raw value in m³/s.
     */
    val cubicMetersPerSecond: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [FlowRate] instance.
     */
    override fun createFromValue(value: Number): FlowRate = FlowRate(value)

    companion object {
        const val BASE_SYMBOL = "m³/s"

        /**
         * Converts a number holding a flow rate value to a [FlowRate] instance.
         * @param value The number to interpret as m³/s.
         * @return Returns a [FlowRate] instance.
         */
        fun fromCubicMetersPerSecond(value: Number): FlowRate = FlowRate(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): FlowRate = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.m3s },
                CreatorInfo("l/s") { value -> value.ls },
            )
    }
}

/**
 * Converts a number holding an m³/s value to a [FlowRate] instance.
 */
val Number.m3s: FlowRate get() = FlowRate.fromCubicMetersPerSecond(this)

/**
 * Converts a number holding an l/s value to a [FlowRate] instance.
 */
val Number.ls: FlowRate get() = FlowRate.fromCubicMetersPerSecond(this.toRawType().divideBy(1000.toRawType()))

/**
 * Creates a pair of a value and associated unit from a scaled flow rate quantity and 'm³/s'.
 */
val ScaledQuantity<FlowRate>.m3s get() = valueWithUnit(this, FlowRate.BASE_SYMBOL)
val ScaledQuantity<FlowRate>.ls get() = valueWithUnit(this, "l/s") { value -> value.times(1000.toRawType()) }

/**
 * Creates a pair of a value and associated unit from a non-scaled flow rate quantity and 'm³/s'.
 */
val FlowRate.m3s get() = valueWithUnit(FlowRate.BASE_SYMBOL)
val FlowRate.ls get() = valueWithUnit("l/s") { value -> value.times(1000.toRawType()) }
