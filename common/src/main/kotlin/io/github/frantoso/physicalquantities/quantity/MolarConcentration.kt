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
 * A class to hold a type and unit safe molar concentration value in Mole per cubic Meters (mol/m³).
 * @param value The raw value.
 */
class MolarConcentration private constructor(
    value: Number,
) : SimpleQuantity<MolarConcentration, MolarConcentration>(value, BASE_SYMBOL),
    Comparable<MolarConcentration> {
    /**
     * Gets the raw value in Mole per cubic Meters (mol/m³).
     */
    val molePerCubicMeters: RawType get() = value

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created [MolarConcentration] instance.
     */
    override fun createFromValue(value: Number): MolarConcentration = MolarConcentration(value)

    companion object {
        const val BASE_SYMBOL = "mol/m³"

        /**
         * Converts a number holding a molar concentration value to a [MolarConcentration] instance.
         * @param value The number to interpret as mol/m³.
         * @return Returns a [MolarConcentration] instance.
         */
        fun fromMolePerCubicMeters(value: Number): MolarConcentration = MolarConcentration(value)

        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param input The instance to convert.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        fun fromValueWithUnit(input: ValueWithUnit): MolarConcentration = fromValueWithUnit(input, creators)

        /**
         * Gets a list of creator functions to generate a new instance from a symbol.
         */
        private val creators =
            listOf(
                CreatorInfo(BASE_SYMBOL) { value -> value.molPerM3 },
                CreatorInfo("mol/l") { value -> value.molPerL },
                CreatorInfo("M") { value -> value.M },
            )
    }
}

/**
 * Converts a number holding a mol per cubic meter value to a [MolarConcentration] instance.
 */
val Number.molPerM3: MolarConcentration get() = MolarConcentration.fromMolePerCubicMeters(this)

/**
 * Converts a number holding a mol per liter value to a [MolarConcentration] instance.
 */
val Number.molPerL: MolarConcentration get() = MolarConcentration.fromMolePerCubicMeters(toRawType().times(1000.toRawType()))

/**
 * Converts a number holding a molar value to a [MolarConcentration] instance.
 */
val Number.M: MolarConcentration get() = MolarConcentration.fromMolePerCubicMeters(toRawType().times(1000.toRawType()))

/**
 * Creates a pair of a value and associated unit from a scaled molar concentration quantity and 'mol/m³'.
 */
val ScaledQuantity<MolarConcentration>.molPerM3 get() = valueWithUnit(this, MolarConcentration.BASE_SYMBOL)
val ScaledQuantity<MolarConcentration>.molPerL
    get() = valueWithUnit(this, "mol/l") { value -> value.divideBy(1000.toRawType()) }
val ScaledQuantity<MolarConcentration>.M get() = valueWithUnit(this, "M") { value -> value.divideBy(1000.toRawType()) }

/**
 * Creates a pair of a value and associated unit from a non-scaled molar concentration quantity and 'mol/m³'.
 */
val MolarConcentration.molPerM3 get() = valueWithUnit(MolarConcentration.BASE_SYMBOL)
val MolarConcentration.molPerL get() = valueWithUnit("mol/l") { value -> value.divideBy(1000.toRawType()) }
val MolarConcentration.M get() = valueWithUnit("M") { value -> value.divideBy(1000.toRawType()) }
