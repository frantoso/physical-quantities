package io.github.frantoso.physicalquantities.thermodynamic

import io.github.frantoso.physicalquantities.core.NoSuchPrefixException
import io.github.frantoso.physicalquantities.core.NoSuchUnitException
import io.github.frantoso.physicalquantities.core.ScaledQuantity
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.core.ValueWithUnit
import io.github.frantoso.physicalquantities.core.valueWithUnit
import io.github.frantoso.physicalquantities.utils.toBigDecimal
import java.math.BigDecimal

/**
 * A class to hold a type and unit safe pressure value in Pascal (Pa).
 * @param value The raw value.
 */
class Pressure private constructor(
    value: Number,
) : SimpleQuantity<Pressure, Pressure>(value, BASE_SYMBOL),
    Comparable<Pressure> {
    /**
     * Gets the raw value in Pascal (Pa).
     */
    val pascal: BigDecimal get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [Pressure] instance.
     */
    override fun createFromValue(value: Number): Pressure = Pressure(value)

    companion object {
        const val BASE_SYMBOL = "Pa"

        /**
         * Converts a number holding a pressure value to a [Pressure] instance.
         * @param value The number to interpret as Pascal.
         * @return Returns a [Pressure] instance.
         */
        fun fromPascal(value: Number): Pressure = Pressure(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): Pressure = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        private val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.Pa },
                CreatorInfo("bar") { value -> value.bar },
            )
    }
}

/**
 * Converts a number holding a Pascal value to a [Pressure] instance.
 */
val Number.Pa: Pressure get() = Pressure.fromPascal(this)

/**
 * Converts a number holding a bar value to a [Pressure] instance.
 */
val Number.bar: Pressure get() = Pressure.fromPascal(toBigDecimal().multiply(BigDecimal(100_000)))

/**
 * Creates a pair of a value and associated unit from a scaled pressure quantity and the unit.
 */
val ScaledQuantity<Pressure>.Pa get() = valueWithUnit(this, Pressure.BASE_SYMBOL)
val ScaledQuantity<Pressure>.bar get() = valueWithUnit(this, "bar") { value -> value.divide(BigDecimal(100_000)) }

/**
 * Creates a pair of a value and associated unit from a non-scaled pressure quantity and the unit.
 */
val Pressure.Pa get() = valueWithUnit(Pressure.BASE_SYMBOL)
val Pressure.bar get() = valueWithUnit("bar") { value -> value.divide(BigDecimal(100_000)) }
