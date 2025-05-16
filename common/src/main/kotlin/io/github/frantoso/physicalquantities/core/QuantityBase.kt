package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.RawType
import io.github.frantoso.physicalquantities.utils.absoluteValue
import io.github.frantoso.physicalquantities.utils.defaultPrecisionFraction
import io.github.frantoso.physicalquantities.utils.hash
import io.github.frantoso.physicalquantities.utils.sgn
import io.github.frantoso.physicalquantities.utils.toRawType

/**
 * Base class for quantities.
 *
 * @property value The raw value to store in this instance. Raw values unit is the reference unit of the quantity.
 * @constructor Initializes a new instance of the [QuantityBase] class.
 */
abstract class QuantityBase protected constructor(
    value: Number,
) {
    /**
     * Gets the raw value stored in this instance. Raw values unit is the reference unit of the quantity.
     */
    internal val value: RawType = value.toRawType()

    /**
     * Gets the raw value stored in this instance. Raw values unit is the reference unit of the quantity.
     * This property is not intended for normal usage.
     */
    val rawValueNotForNormalUsage: RawType
        get() = value

    /**
     * Returns `true` if this instance is equal to [other]; `false` otherwise.
     */
    override fun equals(other: Any?): Boolean =
        (other != null) && (this::class == other::class) && value.compareTo((other as QuantityBase).value) == 0

    /**
     * Returns `true` if the absolute difference between this instance and [other] is smaller than [precisionFraction];
     * `false` otherwise. The default of [precisionFraction] is [defaultPrecisionFraction].
     */
    fun equalsByPrecision(
        other: Any?,
        precisionFraction: RawType = defaultPrecisionFraction,
    ): Boolean =
        (other != null) && (this::class == other::class) && ((value - (other as QuantityBase).value).absoluteValue < precisionFraction)

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int = value.hash

    /**
     * Returns a string representation of the object (it's value).
     */
    override fun toString(): String = value.toString()

    /**
     * Gets the sign of this instance:
     *   - `-1.0` if the value is negative,
     *   - zero if the value is zero,
     *   - `1.0` if the value is positive
     *
     * Special case:
     *   - `NaN.sign` is `NaN`
     */
    val sign: Int = this.value.sgn

    /**
     * Copies this object.
     * @return Returns a copy of this object.
     */
    abstract fun copy(): QuantityBase
}
