package io.github.frantoso.physicalquantities.core

import kotlin.math.sign

/**
 * Base class for quantities.
 *
 * @property value The raw value to store in this instance. Raw values unit is the reference unit of the quantity.
 * @constructor Initializes a new instance of the [QuantityBase] class.
 */
abstract class QuantityBase protected constructor(
    internal val value: Double,
) {
    /**
     * Returns `true` if this instance is equal to [other]; `false` otherwise.
     */
    override fun equals(other: Any?): Boolean =
        (other != null) && (this::class == other::class) && value.equals((other as QuantityBase).value)

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int = value.hashCode()

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
    val sign: Double = value.sign
}
